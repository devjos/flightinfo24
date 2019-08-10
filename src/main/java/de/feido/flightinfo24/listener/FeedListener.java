package de.feido.flightinfo24.listener;

import de.feido.flightinfo24.flightradar.Feed;

public interface FeedListener {

	public void onFeed(Feed feed);

	public void cleanUp();

}
