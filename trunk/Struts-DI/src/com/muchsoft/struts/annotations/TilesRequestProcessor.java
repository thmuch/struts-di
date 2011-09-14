package com.muchsoft.struts.annotations;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;

import com.muchsoft.util.ServiceLocator;
import com.muchsoft.util.naming.JndiNamingStrategy;

public class TilesRequestProcessor extends
		org.apache.struts.tiles.TilesRequestProcessor implements
		InjectionProcessor {

	@Override
	protected Action processActionCreate(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException {

		String className = mapping.getType();

		Action instance;

		synchronized (actions) {

			boolean isNewInstance = (actions.get(className) == null);

			instance = super.processActionCreate(request, response, mapping);

			if (isNewInstance) {
				InjectionHelper.injectEJB(instance, instance.getClass());
			}
		}

		return instance;
	}

	public void setNamingStrategy(String namingStrategyClassName)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		JndiNamingStrategy namingStrategy = (JndiNamingStrategy) Class.forName(
				namingStrategyClassName).newInstance();

		ServiceLocator.setNamingStrategy(namingStrategy);
	}
}
