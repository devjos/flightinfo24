package de.feido.flightinfo24.log;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import de.feido.flightinfo24.model.FlightPosition;

public class FlightPositionFileLoggerTest {

	private final Path LOGFILE = Paths.get("test", "logs", "flights.log");

	@After
	public void deleteLogFileAfter() throws IOException {
		Files.deleteIfExists(LOGFILE);
	}

	@Test
	public void writesLogFile() throws IOException {
		final FlightPositionFileLogger logger = new FlightPositionFileLogger();

		List<String> lines = Files.readAllLines(LOGFILE);
		assertEquals(1, lines.size());
		assertEquals("time,latitude,longitude,speed,altitude,flightnumber,airline,aircraft,callsign", lines.get(0));

		final FlightPosition pos = new FlightPosition(1565422385, 1.2, 3.4d, 123, 38000, "LH304", "LH", "A320",
				"DLH4JF");
		logger.log(pos);
		lines = Files.readAllLines(LOGFILE);
		assertEquals(2, lines.size());
		assertEquals(pos.toString(), lines.get(1));

		final FlightPosition pos2 = new FlightPosition(1565422385, 3.2, 3.1d, 432, 30000, "EY7", "EY", "B777",
				"DLH4JF");
		logger.log(pos2);
		lines = Files.readAllLines(LOGFILE);
		assertEquals(3, lines.size());
		assertEquals(pos2.toString(), lines.get(2));

	}
}
