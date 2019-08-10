package de.feido.flightinfo24;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import de.feido.flightinfo24.flightradar.Flight;
import de.feido.flightinfo24.model.Coordinates;

public class Config {

	private final Coordinates location;
	private final String origin;
	private final int pingInterval;

	public Config(Coordinates coordinates, String origin, int pingInterval) {
		this.location = coordinates;
		this.origin = origin;
		this.pingInterval = pingInterval;
	}

	public Coordinates getLocation() {
		return location;
	}

	public String getOrigin() {
		return origin;
	}

	/**
	 * The ping interval in seconds.
	 * 
	 * @return
	 */
	public int getPingInterval() {
		return pingInterval;
	}

	public boolean testOrigin(Flight flight) {
		return this.origin == null || this.origin.isEmpty() || this.origin.equalsIgnoreCase(flight.getOrigin());
	}

	public boolean testOrigin(String origin) {
		return this.origin == null || this.origin.isEmpty() || this.origin.equalsIgnoreCase(origin);
	}

	public static Config fromFile(File f) throws IOException {
		return new Gson().fromJson(new FileReader(f), Config.class);
	}

}
