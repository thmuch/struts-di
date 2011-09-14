package com.muchsoft.util.naming;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.TestManager;

public class GlassFishNamingStrategyTest {

	private JndiNamingStrategy namingStrategy;

	@Before
	public void setup() {
		namingStrategy = new GlassFishNamingStrategy();
	}

	@Test
	public void testGlassFishNaming() {
		String jndiName = namingStrategy.getJndiName(TestManager.class, null);
		assertEquals("util.TestManager", jndiName);
	}
}
