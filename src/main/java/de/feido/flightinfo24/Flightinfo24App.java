package de.feido.flightinfo24;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.FeedURI;
import de.feido.flightinfo24.http.HttpService;
import de.feido.flightinfo24.listener.DepartingFlightListener;
import de.feido.flightinfo24.listener.FeedListener;
import de.feido.flightinfo24.log.FlightPositionFileLogger;

/**
 *
 * @author johannes
 *
 */
public class Flightinfo24App {

	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		Config config;
		try {
			config = Config.fromFile(new File("config.json"));
		} catch (final IOException e) {
			LOG.fatal("Could not read config.json.", e);
			return;
		}

		final CookieManager cm = new CookieManager();
		CookieHandler.setDefault(cm);
		final HttpClient client = HttpClient.newBuilder() //
				.cookieHandler(CookieHandler.getDefault()) //
				.build();

		// first decimal place is worth up to 11.1 km
		// https://gis.stackexchange.com/questions/8650/measuring-accuracy-of-latitude-and-longitude
		final FeedURI uri = FeedURI.fromSquare(config.getLocation(), 0.1f);

		final HttpService httpService = new HttpService(client);

		final Context ctx = new Context(config, httpService);
		final FlightPositionFileLogger logger = new FlightPositionFileLogger();

		final List<FeedListener> listener = new ArrayList<FeedListener>();
		listener.add(new DepartingFlightListener(ctx, logger));

		while (true) {

			LOG.info("Update feed");
			try {
				final Feed feed = httpService.sendRequest(uri);
				listener.forEach(l -> l.onFeed(feed));
			} catch (InterruptedException | ExecutionException e) {
				if (e.getCause() instanceof ConnectException) {
					LOG.warn("Could not connect.");
					LOG.debug("Could not connect", e);
				} else {
					LOG.error("Error getting flight data", e);
				}
			}

			LOG.info("Clean up.");
			listener.forEach(l -> l.cleanUp());

			LOG.info("Sleep {} seconds.", config.getPingInterval());
			try {
				Thread.sleep(config.getPingInterval() * 1000);
			} catch (final InterruptedException e) {
				LOG.warn("Interrupted while sleeping.", e);
			}

		}

	}
}
