package de.feido.flightinfo24;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.http.FeedAdapter;

public class Util {

	public static Feed feedFromFile(File f) throws IOException {
		final Gson gson = new GsonBuilder() //
				.registerTypeAdapter(Feed.class, new FeedAdapter()) //
				.create();
		try (FileReader fr = new FileReader(f)) {
			return gson.fromJson(fr, Feed.class);
		}
	}

}
