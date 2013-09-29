package org.nishen.timesheet.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class TimesheetApplication extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> s = new HashSet<Class<?>>();

		s.add(TimesheetResource.class);

		return s;
	}
}
