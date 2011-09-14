package com.muchsoft.util.naming;

import javax.ejb.EJB;

public interface JndiNamingStrategy {

	/**
	 * <p>
	 * Creates a JNDI name for an EJB business interface. These lookup names
	 * differ between application servers, especially prior to Java EE 6.
	 * <p>
	 * If the EJB annotation's "name" attribute is set, it is used as the JNDI
	 * name, and the naming strategy (hence this method) is never called.
	 * <p>
	 * Naming strategies need not evaluate both parameters if either the class
	 * (type) name or the annotation are sufficient for certain application
	 * servers.
	 * 
	 * @param cls
	 *            the field type for the EJB to be injected
	 * @param annotation
	 *            the @EJB annotation at the field type
	 * @return
	 */
	public String getJndiName(Class<?> cls, EJB annotation);

}