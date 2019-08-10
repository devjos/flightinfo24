package de.feido.flightinfo24.http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.feido.flightinfo24.flightradar.Feed;
import de.feido.flightinfo24.flightradar.Flight;
import de.feido.flightinfo24.flightradar.Flight.Builder;

public class FeedAdapter implements JsonDeserializer<Feed> {

	private static final Logger LOG = LogManager.getLogger();

	@Override
	public Feed deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		final JsonObject jsonObject = json.getAsJsonObject();
		LOG.trace(jsonObject);

		int fullCount = 0;
		int version = 0;
		final List<Flight> flights = new ArrayList<Flight>();

		for (final Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			switch (entry.getKey()) {
			case "full_count":
				fullCount = entry.getValue().getAsInt();
				break;

			case "version":
				version = entry.getValue().getAsInt();
				break;

			case "stats":
				LOG.debug("Ignore stats attribute");
				break;

			default:
				LOG.debug(entry.getKey() + " " + entry.getValue());

				final JsonElement info = entry.getValue();
				final JsonArray array = info.getAsJsonArray();

				if (array.size() != 19) {
					throw new IllegalArgumentException("infos must have size 19!");
				}

				Builder b = new Flight.Builder(entry.getKey());
				b = b.aircraftHex(array.get(0).getAsString()) //
						.latitude(array.get(1).getAsDouble()) //
						.longitude(array.get(2).getAsDouble()) //
						.angle(array.get(3).getAsInt()) //
						.altitude(array.get(4).getAsInt()) //
						.speed(array.get(5).getAsInt()) //
						// 6
						// 7 flight radar reciever id
						.aircraft(array.get(8).getAsString()) //
						.registration(array.get(9).getAsString()) //
						.time(array.get(10).getAsLong()) //
						.origin(array.get(11).getAsString()) //
						.destination(array.get(12).getAsString()) //
						.flightnumber(array.get(13).getAsString()) //
						// 14
						// 15
						.callsign(array.get(16).getAsString()) //
						// 17
						.airline(array.get(18).getAsString());

				flights.add(b.build());
				break;
			}
		}

		return new Feed(fullCount, version, flights);
	}

}
