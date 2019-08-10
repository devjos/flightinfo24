package de.feido.flightinfo24.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightPositionTest {

	@Test
	public void toStringTest() {
		final FlightPosition pos = new FlightPosition(1565422385, 1.2, 3.4d, 123, 38000, "LH304", "LH", "A320");
		assertEquals("1565422385,1.20,3.40,123,38000,LH304,LH,A320", pos.toString());
	}

	@Test
	public void fromLine() {
		final String line = "1565422385,1.20,3.40,123,38000,LH304,LH,A320";

		final FlightPosition pos = FlightPosition.fromLine(line);

		assertEquals(1565422385, pos.getTime());
		assertEquals(1.20, pos.getLongitude(), 0.01);
		assertEquals(3.40, pos.getLatitude(), 0.01);
		assertEquals(123, pos.getSpeed());
		assertEquals(38000, pos.getAltitude());
		assertEquals("LH304", pos.getFlightNumber());
		assertEquals("LH", pos.getAirline());
		assertEquals("A320", pos.getAircraft());
	}
}
