package de.feido.flightinfo24.model;

import java.util.List;

import de.feido.flightinfo24.flightradar.Waypoint;

public class Coordinates {

	private final double longitude;
	private final double latitude;

	public Coordinates(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public Waypoint nearest(List<Waypoint> trail) {
		double minDistance = Double.MAX_VALUE;
		Waypoint nearest = null;

		for (final Waypoint wp : trail) {
			final double d1 = Math.abs(latitude - wp.getLongitude());
			final double d2 = Math.abs(longitude - wp.getLatitude());

			final double distance = Math.hypot(d1, d2);

			if (distance < minDistance) {
				minDistance = distance;
				nearest = wp;
			}
		}

		return nearest;

	}
}
