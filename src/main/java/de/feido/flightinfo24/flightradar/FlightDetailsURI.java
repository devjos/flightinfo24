package de.feido.flightinfo24.flightradar;

import java.net.URI;
import java.util.Locale;

public class FlightDetailsURI {

	private final static String FR24_URL = "https://data-live.flightradar24.com/clickhandler/?version=1.5&flight=%s";

	private final URI uri;

	public FlightDetailsURI(String id) {
		final String url = String.format(Locale.ENGLISH, FR24_URL, id);
		this.uri = URI.create(url);
	}

	public URI getURI() {
		return uri;
	}

}
