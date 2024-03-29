/*
 * File: FacesUtil.java
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.nishen.timesheet.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Generic utility class for JSF.
 * 
 * @author nish.naidoo@gmail.com
 * 
 */
public class FacesUtil
{
	public static void addMessage(String id, String message)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(message);
		facesContext.addMessage(id, facesMessage);
	}

	public static void redirect(String url) throws Exception
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.redirect(url);
	}

	public static String getRequestParameter(String param)
	{
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx == null)
			return null;

		Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
		String paramValue = params.get(param);

		return paramValue;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null)
		{
			return null;
		}

		return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}
}
