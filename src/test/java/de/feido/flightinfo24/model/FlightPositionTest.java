package de.feido.flightinfo24.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightPositionTest {

	@Test
	public void toStringTest() {
		final FlightPosition pos = new FlightPosition(1565422385, 1.2311, 3.412, 123, 38000, "LH304", "LH", "A320",
				"DLH4JF");
		assertEquals("1565422385,1.2311,3.4120,123,38000,LH304,LH,A320,DLH4JF", pos.toString());
	}

}
