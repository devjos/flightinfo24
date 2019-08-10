package de.feido.flightinfo24.flightradar;

import java.util.List;

import com.google.gson.Gson;

public class FlightDetails {

	private FlightIdentification identification;
	private Aircraft aircraft;
	private Airline airline;
	private List<Waypoint> trail;

	public FlightIdentification getIdentification() {
		return identification;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public Airline getAirline() {
		return airline;
	}

	public List<Waypoint> getTrail() {
		return trail;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
