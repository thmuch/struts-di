package com.muchsoft.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	public static <T> T getBean(Class<T> cls) throws NamingException {
		// in Glassfish, session beans use their fully qualified business
		// interface name as their JNDI name by default
		@SuppressWarnings("unchecked")
		T bean = (T) jndiLookup(cls.getName());
		return bean;
	}

	public static Object getResource(String name) throws NamingException {
		return jndiLookup(name);
	}

	private static Object jndiLookup(String name) throws NamingException {
		// not every app server can reuse contexts, so we create a new one with
		// each call:
		InitialContext ctx = new InitialContext();
		return ctx.lookup(name);
	}
}
