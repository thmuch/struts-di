package com.muchsoft.util;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.muchsoft.util.naming.GlassFishNamingStrategy;
import com.muchsoft.util.naming.JndiNamingStrategy;

public class ServiceLocator {

	public static final JndiNamingStrategy DEFAULT_NAMING_STRATEGY = new GlassFishNamingStrategy();

	private static JndiNamingStrategy namingStrategy;

	/**
	 * <p>
	 * Searches for an EJB proxy object. If the @EJB annotation has a "name"
	 * attribute, its value is used for the JNDI lookup. Otherwise, the naming
	 * strategy is called, if it has been set. If it hasn't been set, the
	 * default naming strategy (GlassFish) is used.
	 * <p>
	 * Used by Struts-DI's RequestProcessors (via InjectionHelper class).
	 * 
	 * @param <T>
	 * @param cls
	 *            the field type for the EJB to be injected
	 * @param annotation
	 *            the @EJB annotation at the field type
	 * @return
	 * @throws NamingException
	 * @see #setNamingStrategy(JndiNamingStrategy)
	 */
	public static <T> T getBean(Class<T> cls, EJB annotation)
			throws NamingException {

		final String jndiName;

		if ((annotation != null) && (annotation.name() != null)
				&& (annotation.name().length() > 0)) {

			jndiName = annotation.name();

		} else if (namingStrategy != null) {

			jndiName = namingStrategy.getJndiName(cls, annotation);

		} else {
			// log.warn("Naming strategy not set, using default");
			jndiName = DEFAULT_NAMING_STRATEGY.getJndiName(cls, annotation);
		}

		@SuppressWarnings("unchecked")
		T bean = (T) jndiLookup(jndiName);
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

	/**
	 * <p>
	 * Used by the active Struts-DI RequestProcessor (as configured in
	 * struts-config.xml) to set the JNDI naming strategy for EJB business
	 * interfaces.
	 * <p>
	 * If no naming strategy is configured in struts-config.xml, getBean() uses
	 * the default naming strategy (GlassFish).
	 * 
	 * @param strategy
	 * @see com.muchsoft.struts.annotations.InjectionProcessor#setNamingStrategy(String)
	 * @see #getBean(Class, EJB)
	 * @see #DEFAULT_NAMING_STRATEGY
	 * @see GlassFishNamingStrategy
	 */
	public static void setNamingStrategy(JndiNamingStrategy strategy) {

		if (strategy == null) {
			throw new IllegalArgumentException(
					"Naming strategy must not be null");
		}

		if (namingStrategy != null) {
			throw new IllegalStateException(
					"Naming strategy can be set only once");
		}

		namingStrategy = strategy;
	}
}
