package de.feido.flightinfo24.flightradar;

import com.google.gson.annotations.SerializedName;

public class Waypoint {

	@SerializedName("lat")
	private final double latitude;

	@SerializedName("lng")
	private final double longitude;

	@SerializedName("alt")
	private final int altitude;

	@SerializedName("spd")
	private final int speed;

	@SerializedName("ts")
	private final long time;

	@SerializedName("hd")
	private final int angle;

	public Waypoint(double latitude, double longitude, int altitude, int speed, long time, int angle) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.speed = speed;
		this.time = time;
		this.angle = angle;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getAltitude() {
		return altitude;
	}

	public int getSpeed() {
		return speed;
	}

	public long getTime() {
		return time;
	}

	public int getAngle() {
		return angle;
	}

}
