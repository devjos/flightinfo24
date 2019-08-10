package de.feido.flightinfo24;

import com.google.common.base.Ticker;

import de.feido.flightinfo24.http.HttpService;

public class Context {

	private final Config config;
	private final HttpService http;
	private final Ticker ticker;

	public Context(Config config, HttpService http) {
		this.config = config;
		this.http = http;
		this.ticker = Ticker.systemTicker();
	}

	public Context(Config config, HttpService http, Ticker ticker) {
		this.config = config;
		this.http = http;
		this.ticker = ticker;
	}

	public Config getConfig() {
		return config;
	}

	public HttpService getHttp() {
		return http;
	}

	public Ticker getTicker() {
		return ticker;
	}

}
