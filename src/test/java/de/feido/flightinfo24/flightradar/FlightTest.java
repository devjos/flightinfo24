package de.feido.flightinfo24.flightradar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightTest {

	@Test
	public void toStringTest() {
		final Flight f = new Flight.Builder("21a37c56") //
				.flightnumber("TU595") //
				.build();
		assertEquals("TU595(21a37c56)", f.toString());
	}

}
