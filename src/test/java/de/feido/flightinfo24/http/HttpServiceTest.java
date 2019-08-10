package de.feido.flightinfo24.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.FeedURI;
import de.feido.flightinfo24.flightradar.FlightDetails;
import de.feido.flightinfo24.flightradar.FlightDetailsURI;

public class HttpServiceTest {

	@Test
	public void createsRequest() {
		final FeedURI uri = FeedURI.fromSquare(5.f, 3.f, 0.5f);
		final MyHttpClient client = new MyHttpClient("feed.json");

		final HttpService feedService = new HttpService(client);

//		final var request = feedService.getRequest();
//
//		assertEquals("GET", request.method());
//		assertEquals(uri.getURI(), request.uri());

		// TODO check header values
//		final var headersMap = request.headers().map();
//		System.out.println(headersMap);

	}

	@Test
	public void requestFeed() throws Exception {
		final FeedURI uri = FeedURI.fromSquare(5.f, 3.f, 0.5f);
		final MyHttpClient client = new MyHttpClient("feed.json");

		final HttpService feedService = new HttpService(client);

		final Feed feed = feedService.sendRequest(uri);

		assertEquals(4, feed.getVersion());
		assertEquals(16485, feed.getFullCount());
		assertEquals(3, feed.getFlights().size());

	}

	@Test
	public void requestFlightDetails() throws Exception {
		final FlightDetailsURI uri = new FlightDetailsURI("219f759c");
		final MyHttpClient client = new MyHttpClient("219f759c.json");

		final HttpService feedService = new HttpService(client);

		final FlightDetails flightDetail = feedService.sendRequest(uri);

		assertEquals("219f759c", flightDetail.getIdentification().getId());
		assertEquals("AFR011", flightDetail.getIdentification().getCallsign());

		assertEquals("A388", flightDetail.getAircraft().getCode());
		assertEquals("Airbus A380-861", flightDetail.getAircraft().getText());

		assertEquals(76, flightDetail.getAircraft().getCountryId());
		assertEquals("F-HPJH", flightDetail.getAircraft().getRegistration());
		assertEquals("39bd27", flightDetail.getAircraft().getHex());

		assertEquals("Air France", flightDetail.getAirline().getName());
		assertEquals("Air France", flightDetail.getAirline().getShortName());
		assertEquals("AF", flightDetail.getAirline().getCode().getIata());
		assertEquals("AFR", flightDetail.getAirline().getCode().getIcao());

		assertEquals(323, flightDetail.getTrail().size());
		assertEquals(49.807682, flightDetail.getTrail().get(0).getLatitude(), 0.001);
		assertEquals(-4.871694, flightDetail.getTrail().get(0).getLongitude(), 0.001);
		assertEquals(39000, flightDetail.getTrail().get(0).getAltitude());
		assertEquals(539, flightDetail.getTrail().get(0).getSpeed());
		assertEquals(1565339661, flightDetail.getTrail().get(0).getTime());

	}
}
