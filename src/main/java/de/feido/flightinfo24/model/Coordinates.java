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
			final double distance = Distance.calculateDistance(latitude, longitude, wp.getLatitude(),
					wp.getLongitude());

			if (distance < minDistance) {
				minDistance = distance;
				nearest = wp;
			}
		}

		return nearest;

	}
}
