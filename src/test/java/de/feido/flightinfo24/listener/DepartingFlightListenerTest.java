package de.feido.flightinfo24.listener;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import de.feido.flightinfo24.Config;
import de.feido.flightinfo24.Context;
import de.feido.flightinfo24.FakeTicker;
import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.FeedURI;
import de.feido.flightinfo24.http.HttpService;
import de.feido.flightinfo24.http.MyHttpClient;
import de.feido.flightinfo24.log.CountingFlightPositionLogger;
import de.feido.flightinfo24.model.Coordinates;

public class DepartingFlightListenerTest {

	@Test
	public void logsEntry() throws Exception {

		final Coordinates c = new Coordinates(48.19d, 11.79d);
		final Config config = new Config(c, "MUC", 15);
		final HttpService http = new HttpService(new MyHttpClient("feedMucSimple.json", "21a34ab8.json"));
		final FakeTicker ticker = new FakeTicker();
		final Context ctx = new Context(config, http, ticker);
		final CountingFlightPositionLogger logger = new CountingFlightPositionLogger();
		final DepartingFlightListener l = new DepartingFlightListener(ctx, logger);
		assertEquals(0, l.cacheSize());

		final Feed feed = http.sendRequest(FeedURI.fromSquare(c, 0.05d));

		l.onFeed(feed);

		assertEquals(1, l.cacheSize());
		ticker.advance(4, TimeUnit.MINUTES);
		l.cleanUp();

		assertEquals(0, l.cacheSize());
		assertEquals(1, logger.getLogEntryCount());
	}

	@Test
	public void doNotLogEntryIfTooFarAway() throws Exception {

		final Coordinates c = new Coordinates(10.79d, 48.19d); // fixed location
		final Config config = new Config(c, "MUC", 15);
		final HttpService http = new HttpService(new MyHttpClient("feedMucSimple.json", "21a34ab8.json"));
		final FakeTicker ticker = new FakeTicker();
		final Context ctx = new Context(config, http, ticker);
		final CountingFlightPositionLogger logger = new CountingFlightPositionLogger();
		final DepartingFlightListener l = new DepartingFlightListener(ctx, logger);
		assertEquals(0, l.cacheSize());

		final Feed feed = http.sendRequest(FeedURI.fromSquare(c, 0.05d));

		l.onFeed(feed);

		assertEquals(1, l.cacheSize());
		ticker.advance(4, TimeUnit.MINUTES);
		l.cleanUp();

		assertEquals(0, l.cacheSize());
		assertEquals(0, logger.getLogEntryCount());
	}

	@Test
	public void cacheContainsEntryOnlyOnce() throws Exception {

		final Coordinates c = new Coordinates(11.79d, 48.19d);
		final Config config = new Config(c, "MUC", 15);
		final HttpService http = new HttpService(new MyHttpClient("feedMucSimple.json", "21a34ab8.json"));
		final Context ctx = new Context(config, http);
		final CountingFlightPositionLogger logger = new CountingFlightPositionLogger();
		final DepartingFlightListener l = new DepartingFlightListener(ctx, logger);
		assertEquals(0, l.cacheSize());

		final Feed feed = http.sendRequest(FeedURI.fromSquare(c, 0.05d));

		assertEquals(0, l.cacheSize());
		l.onFeed(feed);
		assertEquals(1, l.cacheSize());
		l.onFeed(feed);
		assertEquals(1, l.cacheSize());
		assertEquals(0, logger.getLogEntryCount()); // no entry was removed and logged

	}

}
