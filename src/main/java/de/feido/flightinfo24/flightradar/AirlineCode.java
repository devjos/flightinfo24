package de.feido.flightinfo24.flightradar;

public class AirlineCode {

	private final String iata;
	private final String icao;

	public AirlineCode(String iata, String icao) {
		this.iata = iata;
		this.icao = icao;
	}

	public String getIata() {
		return iata;
	}

	public String getIcao() {
		return icao;
	}

}
