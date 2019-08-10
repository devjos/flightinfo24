package de.feido.flightinfo24.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.FeedURI;
import de.feido.flightinfo24.flightradar.Flight;
import de.feido.flightinfo24.flightradar.FlightDetails;
import de.feido.flightinfo24.flightradar.FlightDetailsURI;

public class HttpService {

	private final Gson gson = new GsonBuilder() //
			.registerTypeAdapter(Feed.class, new FeedAdapter()) //
			.create();
	private final HttpClient client;

	public HttpService(HttpClient client) {
		this.client = client;
	}

	private HttpRequest buildRequest(URI uri) {
		return HttpRequest.newBuilder() //
				.GET() //
				.uri(uri) //
				.header("Accept", "application/json, text/javascript, */*; q=0.01") //
				.header("Accept-Language", "en-US,en;q=0.5") //
				.header("Cache-Control", "no-cache") //
//				.header("Connection", "keep-alive") // restricted header
//				.header("Host", "data-live.flightradar24.com") // restricted header
				.header("Origin", "https://www.flightradar24.com") //
				.header("Pragma", "no-cache") //
				.header("TE", "Trailers") //
				.header("User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:68.0) Gecko/20100101 Firefox/68.0")
				.build();
	}

	public Feed sendRequest(FeedURI uri) throws InterruptedException, ExecutionException {
		final HttpRequest request = buildRequest(uri.getURI());
		return client.sendAsync(request, BodyHandlers.ofString()) //
				.thenApply(res -> res.body()) //
				.thenApply(body -> gson.fromJson(body, Feed.class)) //
				.get();
	}

	public FlightDetails sendRequest(Flight flight) throws InterruptedException, ExecutionException {
		return sendRequest(new FlightDetailsURI(flight.getId()));
	}

	public FlightDetails sendRequest(FlightDetailsURI uri) throws InterruptedException, ExecutionException {
		final HttpRequest request = buildRequest(uri.getURI());
		return client.sendAsync(request, BodyHandlers.ofString()) //
				.thenApply(res -> res.body()) //
				.thenApply(body -> gson.fromJson(body, FlightDetails.class)) //
				.get();
	}

}
