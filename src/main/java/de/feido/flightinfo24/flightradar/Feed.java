package de.feido.flightinfo24.flightradar;

import java.util.List;

import com.google.gson.Gson;

public class Feed {

	private final int fullCount;
	private final int version;
	private final List<Flight> flights;

	public Feed(int fullCount, int version, List<Flight> flights) {
		this.fullCount = fullCount;
		this.version = version;
		this.flights = flights;
	}

	public int getVersion() {
		return this.version;
	}

	public int getFullCount() {
		return this.fullCount;
	}

	public List<Flight> getFlights() {
		return this.flights;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
