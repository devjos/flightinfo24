package de.feido.flightinfo24.flightradar;

public class AircraftModel {

	private final String code;
	private final String text;

	public AircraftModel(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
}
