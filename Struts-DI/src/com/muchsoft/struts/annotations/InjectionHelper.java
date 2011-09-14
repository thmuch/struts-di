package com.muchsoft.struts.annotations;

import java.lang.reflect.Field;

import javax.ejb.EJB;

import org.apache.struts.action.Action;

import com.muchsoft.util.ServiceLocator;

class InjectionHelper {

	static void injectEJB(final Action instance, Class<?> cls) {

		Field[] fields = cls.getDeclaredFields();

		for (Field f : fields) {

			EJB ejbAnnotation = f.getAnnotation(EJB.class);

			if (ejbAnnotation != null) {

				Class<?> type = f.getType();

				try {
					Object ejb = ServiceLocator.getBean(type, ejbAnnotation);
					f.setAccessible(true);
					f.set(instance, ejb);
				} catch (Exception e) {
					throw new RuntimeException("Failed to inject @EJB \""
							+ type.getName() + "\" into Struts Action \""
							+ instance.getClass().getName() + "\"", e);
				}
			}
		}

		cls = cls.getSuperclass();

		if (cls != null) {
			injectEJB(instance, cls);
		}
	}
}
