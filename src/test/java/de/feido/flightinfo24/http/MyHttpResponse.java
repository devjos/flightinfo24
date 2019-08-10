package de.feido.flightinfo24.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.net.ssl.SSLSession;

public class MyHttpResponse<T> implements HttpResponse<T> {
	private final String file;

	public MyHttpResponse(String file) {
		this.file = file;
	}

	@Override
	public int statusCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HttpRequest request() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HttpResponse<T>> previousResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpHeaders headers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T body() {
		final URL url = getClass().getClassLoader().getResource(file);
		try {
			return (T) Files.readString(Paths.get(url.toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException("Error reading file", e);
		}

	}

	@Override
	public Optional<SSLSession> sslSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI uri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version version() {
		// TODO Auto-generated method stub
		return null;
	}

}
