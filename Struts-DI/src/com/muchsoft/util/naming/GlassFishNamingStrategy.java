package com.muchsoft.util.naming;

import javax.ejb.EJB;

/**
 * <p>
 * In GlassFish v2 (Java EE 5), session beans use their fully qualified business
 * interface name as their JNDI name by default.
 * <p>
 * In GlassFish v3 (Java EE 6) this naming schema is still supported (in
 * addition to global standard names).
 */
public class GlassFishNamingStrategy implements JndiNamingStrategy {

	public String getJndiName(Class<?> cls, EJB annotation) {
		return cls.getName();
	}
}
