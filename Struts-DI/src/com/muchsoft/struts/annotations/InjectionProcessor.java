package com.muchsoft.struts.annotations;

interface InjectionProcessor {

	/**
	 * Used by Struts to set the JNDI EJB naming strategy as configured in
	 * struts-config.xml (element "controller", child element "set-property").
	 * 
	 * @param namingStrategyClassName
	 *            a valid, fully qualified class name
	 * @throws Exception
	 *             if the class name is invalid, the class is not available on
	 *             the class path, or the class cannot be instantiated
	 */
	public void setNamingStrategy(String namingStrategyClassName)
			throws Exception;
}
