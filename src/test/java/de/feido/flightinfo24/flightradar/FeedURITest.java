package de.feido.flightinfo24.flightradar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FeedURITest {

	@Test
	public void fromBoundaries() {
		final FeedURI uri = FeedURI.fromBoundaries(1.2f, 0.2f, 4.1f, 4.8f);
		assertEquals(
				"https://data-live.flightradar24.com/zones/fcgi/feed.js?bounds=1.20,0.20,4.10,4.80&faa=1&satellite=1&mlat=1&flarm=1&adsb=1&gnd=1&air=1&vehicles=1&estimated=1&maxage=14400&gliders=1&stats=1",
				uri.getURI().toString());
	}

	@Test
	public void fromCenter() {
		final FeedURI uri = FeedURI.fromSquare(8.5, 5, 4);
		assertEquals(
				"https://data-live.flightradar24.com/zones/fcgi/feed.js?bounds=10.50,6.50,3.00,7.00&faa=1&satellite=1&mlat=1&flarm=1&adsb=1&gnd=1&air=1&vehicles=1&estimated=1&maxage=14400&gliders=1&stats=1",
				uri.getURI().toString());
	}

}
