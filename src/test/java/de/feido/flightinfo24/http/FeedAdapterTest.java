package de.feido.flightinfo24.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.Flight;

public class FeedAdapterTest {

	File feedFile = new File(getClass().getClassLoader().getResource("feed.json").getFile());

	@Test
	public void canParseFeedResponse() {

		final Gson gson = new GsonBuilder() //
				.registerTypeAdapter(Feed.class, new FeedAdapter()) //
				.create();

		try (FileReader fr = new FileReader(feedFile)) {

			final Feed feed = gson.fromJson(fr, Feed.class);

			assertEquals(4, feed.getVersion());
			assertEquals(16485, feed.getFullCount());
			assertEquals(3, feed.getFlights().size());

			final Flight flight = feed.getFlights().get(1);
			assertEquals("219e6e94", flight.getId());
			assertEquals("406321", flight.getAircraftHex());
			assertEquals("A319", flight.getAircraft());
			assertEquals("EZY", flight.getAirline());
			assertEquals("EZY8187", flight.getCallsign());
			assertEquals("LGW", flight.getOrigin());
			assertEquals("MXP", flight.getDestination());
			assertEquals(322, flight.getSpeed());
			assertEquals(14475, flight.getAltitude());

		} catch (final IOException e) {
			fail(e.getMessage());
		}

	}
}
