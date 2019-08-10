package de.feido.flightinfo24.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.feido.flightinfo24.flightradar.Waypoint;

public class CoordinatesTest {

	@Test
	public void simple() {
		final Coordinates c = new Coordinates(1.2d, 3.4d);
		assertEquals(1.2d, c.getLongitude(), 0.01d);
		assertEquals(3.4d, c.getLatitude(), 0.01d);
	}

	@Test
	public void nearest() {
		final Coordinates c = new Coordinates(1.2d, 3.4d);

		final List<Waypoint> waypoints = new ArrayList<Waypoint>();
		waypoints.add(new Waypoint(5.5d, 8.7d, 35000, 200, 3, 5));
		waypoints.add(new Waypoint(1.5d, 3.7d, 35000, 200, 3, 5));
		final Waypoint expectedNearest = new Waypoint(1.4d, 3.3d, 32000, 200, 3, 5);
		waypoints.add(expectedNearest);
		waypoints.add(new Waypoint(15.5d, 8.7d, 32000, 200, 3, 5));

		assertEquals(expectedNearest, c.nearest(waypoints));
	}

}
