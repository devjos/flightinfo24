package de.feido.flightinfo24;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ConfigTest {

	@Test
	public void parse() throws IOException {
		final Config c = Config.fromFile(new File("config.json"));
		assertEquals(11.79d, c.getLocation().getLongitude(), 0.001d);
		assertEquals(48.19d, c.getLocation().getLatitude(), 0.001d);
		assertEquals("MUC", c.getOrigin());
		assertEquals(25, c.getPingInterval());
	}

	@Test
	public void testOriginIfSet() throws IOException {
		final Config c = new Config(null, "MUC", 15);
		assertTrue(c.testOrigin("MUC"));
		assertTrue(c.testOrigin("muc"));
		assertFalse(c.testOrigin("DBX"));
	}

	@Test
	public void testOriginIfNull() throws IOException {
		final Config c = new Config(null, null, 15);
		assertTrue(c.testOrigin("MUC"));
		assertTrue(c.testOrigin("muc"));
		assertTrue(c.testOrigin("DBX"));
	}

	@Test
	public void testOriginIfEMpty() throws IOException {
		final Config c = new Config(null, "", 15);
		assertTrue(c.testOrigin("MUC"));
		assertTrue(c.testOrigin("muc"));
		assertTrue(c.testOrigin("DBX"));
	}

}
