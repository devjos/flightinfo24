package de.feido.flightinfo24.http;

import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

public class MyHttpClient extends HttpClient {

	private int count = 0;
	private final String[] files;

	public MyHttpClient(String... files) {
		this.files = files;
	}

	@Override
	public Optional<CookieHandler> cookieHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Duration> connectTimeout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Redirect followRedirects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProxySelector> proxy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SSLContext sslContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SSLParameters sslParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Authenticator> authenticator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version version() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Executor> executor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler)
			throws IOException, InterruptedException {
		final MyHttpResponse<T> response = new MyHttpResponse<T>(files[count]);
		count++;
		return response;
	}

	@Override
	public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler) {
		final CompletableFuture<HttpResponse<T>> f = new CompletableFuture<HttpResponse<T>>();
		Executors.newCachedThreadPool().submit(() -> {
			f.complete(send(request, responseBodyHandler));
			return null;
		});
		return f;
	}

	@Override
	public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler,
			PushPromiseHandler<T> pushPromiseHandler) {
		// TODO Auto-generated method stub
		return null;
	}

}
