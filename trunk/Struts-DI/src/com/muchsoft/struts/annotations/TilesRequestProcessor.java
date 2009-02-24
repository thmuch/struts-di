package com.muchsoft.struts.annotations;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;

public class TilesRequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor {

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
				InjectionHelper.injectEJB(instance, instance
						.getClass());
			}
		}

		return instance;
	}

}
