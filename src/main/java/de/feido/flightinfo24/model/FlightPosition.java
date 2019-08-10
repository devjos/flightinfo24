package de.feido.flightinfo24.model;

import java.util.Locale;

public class FlightPosition {

	private final long time;
	private final double longitude;
	private final double latitude;
	private final int speed;
	private final int altitude;
	private final String flightnumber;
	private final String airline;
	private final String aircraft;

	public FlightPosition(long time, double longitude, double latitude, int speed, int altitude, String flightnumber,
			String airline, String aircraft) {
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speed = speed;
		this.altitude = altitude;
		this.flightnumber = flightnumber;
		this.airline = airline;
		this.aircraft = aircraft;
	}

	public long getTime() {
		return time;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public int getSpeed() {
		return speed;
	}

	public int getAltitude() {
		return altitude;
	}

	public String getFlightNumber() {
		return flightnumber;
	}

	public String getAirline() {
		return airline;
	}

	public String getAircraft() {
		return aircraft;
	}

	public static FlightPosition fromLine(String line) {
		final String[] attributes = line.split(",");
		if (attributes.length != 8) {
			throw new IllegalArgumentException("Must have exactly 8 arguments, found " + attributes.length);
		}
		final long time = Long.parseLong(attributes[0]);
		final double longitude = Double.parseDouble(attributes[1]);
		final double latitude = Double.parseDouble(attributes[2]);
		final int speed = Integer.parseInt(attributes[3]);
		final int altitude = Integer.parseInt(attributes[4]);
		final String flightnumber = attributes[5];
		final String airline = attributes[6];
		final String aircraft = attributes[7];
		return new FlightPosition(time, longitude, latitude, speed, altitude, flightnumber, airline, aircraft);
	}

	@Override
	public String toString() {
		return String.format(Locale.ENGLISH, "%d,%.2f,%.2f,%d,%d,%s,%s,%s", time, longitude, latitude, speed, altitude,
				flightnumber, airline, aircraft);
	}
}
