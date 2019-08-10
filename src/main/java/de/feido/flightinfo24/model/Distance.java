package de.feido.flightinfo24.model;

public class Distance {

	public static double calculateDistance(double x1, double y1, double x2, double y2) {

		final double d1 = Math.abs(x1 - x2);
		final double d2 = Math.abs(y1 - y2);

		return Math.hypot(d1, d2);

	}
}
