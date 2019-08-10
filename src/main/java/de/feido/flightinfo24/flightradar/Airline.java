package de.feido.flightinfo24.flightradar;

import com.google.gson.annotations.SerializedName;

public class Airline {

	private final String name;

	@SerializedName("short")
	private final String shortName;

	private final AirlineCode code;

	public Airline(String name, String shortName, AirlineCode code) {
		this.name = name;
		this.shortName = shortName;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public AirlineCode getCode() {
		return code;
	}

}
