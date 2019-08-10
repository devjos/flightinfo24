package de.feido.flightinfo24.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.feido.flightinfo24.model.FlightPosition;

public class FlightPositionFileLogger implements FlightPositionLogger {

	private static final Logger LOG = LogManager.getLogger();

	@Override
	public void log(FlightPosition flightPosition) {
		LOG.info(flightPosition.toString());
	}

}
