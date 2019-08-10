package de.feido.flightinfo24.flightradar;

public class Flight {

	private final String id;

	private final String aircraftHex; // 0 unique 24-bit aircraft address
	private final double latitude; // 1
	private final double longitude; // 2
	private final int angle; // 3
	private final int altitude; // 4
	private final int speed; // 5 unit?
	// 6 ?
	// 7 flight radar reciever id
	private final String aircraft; // 8 aircraft type
	private final String registration; // 9
	private final long time; // 10 utc?
	private final String origin; // 11
	private final String destination; // 12
	private final String flightnumber; // 13 IATA
	// 14?
	// 15?
	private final String callsign;// 16 ICAO?
	// 17?
	private final String airline; // 18 icao

	public Flight(String id, String aircraftHex, double latitude, double longitude, int altitude, int angle, int speed,
			String aircraft, String registration, long time, String origin, String destination, String flightnumber,
			String callsign, String airline) {
		this.id = id;
		this.aircraftHex = aircraftHex;
		this.latitude = latitude;
		this.longitude = longitude;
		this.angle = angle;
		this.altitude = altitude;
		this.speed = speed;
		this.aircraft = aircraft;
		this.registration = registration;
		this.time = time;
		this.origin = origin;
		this.destination = destination;
		this.flightnumber = flightnumber;
		this.callsign = callsign;
		this.airline = airline;
	}

	public String getId() {
		return id;
	}

	public String getAircraftHex() {
		return aircraftHex;
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

	public int getAngle() {
		return angle;
	}

	public int getSpeed() {
		return speed;
	}

	public String getAircraft() {
		return aircraft;
	}

	public String getRegistration() {
		return registration;
	}

	public long getTime() {
		return time;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public String getCallsign() {
		return callsign;
	}

	public String getAirline() {
		return airline;
	}

	@Override
	public String toString() {
		return flightnumber + "(" + id + ")";
	}

	public static class Builder {

		private final String id;
		private String aircraftHex; // 0 unique 24-bit aircraft address
		private double latitude; // 1
		private double longitude; // 2
		private int angle; // 3
		private int altitude; // 4
		private int speed; // 5 unit?
		// 6 ?
		// 7
		private String aircraft; // 8 aircraft type
		private String registration; // 9
		private long time; // 10 utc?
		private String origin; // 11
		private String destination; // 12
		private String flightnumber; // 13 IATA
		// 14?
		// 15?
		private String callsign;// 16 ICAO?
		// 17?
		private String airline; // 18 icao

		public Builder(String id) {
			this.id = id;
		}

		public Builder aircraftHex(String aircraftHex) {
			this.aircraftHex = aircraftHex;
			return this;
		}

		public Builder longitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		public Builder latitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		public Builder altitude(int altitude) {
			this.altitude = altitude;
			return this;
		}

		public Builder angle(int angle) {
			this.angle = angle;
			return this;
		}

		public Builder speed(int speed) {
			this.speed = speed;
			return this;
		}

		public Builder aircraft(String aircraft) {
			this.aircraft = aircraft;
			return this;
		}

		public Builder registration(String registration) {
			this.registration = registration;
			return this;
		}

		public Builder time(long time) {
			this.time = time;
			return this;
		}

		public Builder origin(String origin) {
			this.origin = origin;
			return this;
		}

		public Builder destination(String destination) {
			this.destination = destination;
			return this;
		}

		public Builder flightnumber(String flightnumber) {
			this.flightnumber = flightnumber;
			return this;
		}

		public Builder callsign(String callsign) {
			this.callsign = callsign;
			return this;
		}

		public Builder airline(String airline) {
			this.airline = airline;
			return this;
		}

		public Flight build() {
			return new Flight(id, aircraftHex, latitude, longitude, altitude, angle, speed, aircraft, registration,
					time, origin, destination, flightnumber, callsign, airline);
		}

	}

}
