package de.feido.flightinfo24.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DistanceTest {

	@Test
	public void calcDistance() {
		final double distance = Distance.calculateDistance(1, 1, 4, 5);
		assertEquals(5, distance, 0.0001);
	}

	@Test
	public void calcDistance2() {
		final double distance = Distance.calculateDistance(1, 1, 2, 1);
		assertEquals(1, distance, 0.0001);
	}
}
