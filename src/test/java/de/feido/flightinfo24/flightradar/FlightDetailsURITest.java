package de.feido.flightinfo24.flightradar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightDetailsURITest {

	@Test
	public void createUri1() {
		final FlightDetailsURI uri = new FlightDetailsURI("219fce31");
		assertEquals("https://data-live.flightradar24.com/clickhandler/?version=1.5&flight=219fce31",
				uri.getURI().toString());
	}

	@Test
	public void createUri2() {
		final FlightDetailsURI uri = new FlightDetailsURI("219f1e52");
		assertEquals("https://data-live.flightradar24.com/clickhandler/?version=1.5&flight=219f1e52",
				uri.getURI().toString());
	}

}
