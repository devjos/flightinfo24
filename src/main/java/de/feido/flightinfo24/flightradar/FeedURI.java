package de.feido.flightinfo24.flightradar;

import java.net.URI;
import java.util.Locale;

import de.feido.flightinfo24.model.Coordinates;

public class FeedURI {

	private final static String FR24_URL = "https://data-live.flightradar24.com/zones/fcgi/feed.js?bounds=%.2f,%.2f,%.2f,%.2f&faa=1&satellite=1&mlat=1&flarm=1&adsb=1&gnd=1&air=1&vehicles=1&estimated=1&maxage=14400&gliders=1&stats=1";

	private final URI uri;

	private FeedURI(double upper, double lower, double left, double right) {
		final String url = String.format(Locale.ENGLISH, FR24_URL, upper, lower, left, right);
		this.uri = URI.create(url);
	}

	public URI getURI() {
		return uri;
	}

	public static FeedURI fromSquare(Coordinates c, double sideLength) {
		return FeedURI.fromSquare(c.getLatitude(), c.getLongitude(), sideLength);
	}

	/**
	 * Create the flightradar24 URI. The coordinates are a square around the center
	 * defined by longitude and latitude.
	 *
	 * @param longitude
	 * @param latitude
	 * @param sideLength
	 * @return
	 */
	public static FeedURI fromSquare(double latitude, double longitude, double sideLength) {
		return new FeedURI(latitude + sideLength / 2, latitude - sideLength / 2, longitude - sideLength / 2,
				longitude + sideLength / 2);
	}

	/**
	 * Create the flightradar24 URI. The area is defined by the bounds upper, lower,
	 * left and right.
	 *
	 * @param upper
	 * @param lower
	 * @param left
	 * @param right
	 * @return
	 */
	public static FeedURI fromBoundaries(float upper, float lower, float left, float right) {
		return new FeedURI(upper, lower, left, right);
	}

}
