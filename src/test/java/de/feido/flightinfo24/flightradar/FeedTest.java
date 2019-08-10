package de.feido.flightinfo24.flightradar;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.Flight;

public class FeedTest {

	@Test
	public void create1() {
		final int fullCount = 5;
		final int version = 3;
		final List<Flight> flights = new ArrayList<Flight>();

		final Feed feed = new Feed(fullCount, version, flights);

		assertEquals(fullCount, feed.getFullCount());
		assertEquals(version, feed.getVersion());
		assertEquals(0, feed.getFlights().size());
	}

	@Test
	public void create2() {
		final int fullCount = 19;
		final int version = 4;
		final List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight.Builder("id").build());

		final Feed feed = new Feed(fullCount, version, flights);

		assertEquals(fullCount, feed.getFullCount());
		assertEquals(version, feed.getVersion());
		assertEquals(1, feed.getFlights().size());
	}
}
