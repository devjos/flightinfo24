package de.feido.flightinfo24.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.feido.flightinfo24.model.FlightPosition;

public class CountingFlightPositionLogger implements FlightPositionLogger {

	private static final Logger LOG = LogManager.getLogger();
	private int logEntryCount = 0;

	@Override
	public void log(FlightPosition flightPosition) {
		LOG.debug("Log flight position: {}", flightPosition);
		logEntryCount++;
	}

	public int getLogEntryCount() {
		return logEntryCount;
	}

}
