package de.feido.flightinfo24.listener;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import de.feido.flightinfo24.Context;
import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.Flight;
import de.feido.flightinfo24.flightradar.FlightDetails;
import de.feido.flightinfo24.flightradar.Waypoint;
import de.feido.flightinfo24.log.FlightPositionLogger;
import de.feido.flightinfo24.model.Coordinates;
import de.feido.flightinfo24.model.Distance;
import de.feido.flightinfo24.model.FlightPosition;

public class DepartingFlightListener implements FeedListener, RemovalListener<String, Flight> {

	private static final Logger LOG = LogManager.getLogger();
	private final FlightPositionLogger logger;
	private final Cache<String, Flight> cache;
	private final Context ctx;

	public DepartingFlightListener(Context ctx, FlightPositionLogger logger) {
		this.ctx = ctx;
		this.logger = logger;
		this.cache = CacheBuilder.newBuilder() //
				.maximumSize(100) //
				.ticker(ctx.getTicker()) //
				.expireAfterWrite(3, TimeUnit.MINUTES) //
				.removalListener(this) //
				.build();
	}

	@Override
	public void onFeed(Feed feed) {
		final List<Flight> flights = feed.getFlights();
		LOG.trace("Flights entries:{}", flights.size());
		flights.stream() //
				.filter(ctx.getConfig()::testOrigin) //
				.filter(flight -> cache.getIfPresent(flight.getId()) == null) //
				.forEach(flight -> cache.put(flight.getId(), flight));
	}

	public long cacheSize() {
		return cache.size();
	}

	@Override
	public void cleanUp() {
		cache.cleanUp();
	}

	@Override
	public void onRemoval(RemovalNotification<String, Flight> notification) {
		LOG.debug("Entry removed from cache: {}", notification);
		final Flight flight = notification.getValue();

		try {
			final FlightDetails flightDetails = ctx.getHttp().sendRequest(flight);
			final List<Waypoint> trail = flightDetails.getTrail();

			final Coordinates c = ctx.getConfig().getLocation();
			final Waypoint nearest = c.nearest(trail);

			final double distance = Distance.calculateDistance(c.getLongitude(), c.getLatitude(),
					nearest.getLongitude(), nearest.getLatitude());

			// log only if flight was close enough
			// 0.3 is equal to about 3.33km
			if (distance < 0.3) {
				final FlightPosition pos = new FlightPosition(nearest.getTime(), nearest.getLongitude(),
						nearest.getLatitude(), nearest.getSpeed(), nearest.getAltitude(), flight.getFlightnumber(),
						flight.getAirline(), flight.getAircraft(), flight.getCallsign());
				logger.log(pos);
			} else {
				LOG.info("Flight {} is too far away.", flight.getCallsign());
			}

		} catch (InterruptedException | ExecutionException e) {
			LOG.error("Could not log flight with id: " + flight.getId(), e);
		}

	}

}
