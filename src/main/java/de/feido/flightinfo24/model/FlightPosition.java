package de.feido.flightinfo24.model;

import java.util.Locale;

public class FlightPosition {

	private final long time;
	private final double latitude;
	private final double longitude;
	private final int speed;
	private final int altitude;
	private final String flightnumber;
	private final String airline;
	private final String aircraft;
	private final String callsign;

	public FlightPosition(long time, double latitude, double longitude, int speed, int altitude, String flightnumber,
			String airline, String aircraft, String callsign) {
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.speed = speed;
		this.altitude = altitude;
		this.flightnumber = flightnumber;
		this.airline = airline;
		this.aircraft = aircraft;
		this.callsign = callsign;
	}

	public long getTime() {
		return time;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
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

	public String getCallsign() {
		return callsign;
	}

	@Override
	public String toString() {
		return String.format(Locale.ENGLISH, "%d,%.4f,%.4f,%d,%d,%s,%s,%s,%s", time, latitude, longitude, speed,
				altitude, flightnumber, airline, aircraft, callsign);
	}
}
