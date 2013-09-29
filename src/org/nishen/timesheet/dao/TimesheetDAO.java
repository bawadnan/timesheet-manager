package org.nishen.timesheet.dao;

import java.util.Date;

import org.nishen.timesheet.entity.Timesheet;
import org.nishen.timesheet.entity.TimesheetUser;

public interface TimesheetDAO
{
	public TimesheetUser getUser(String oneId);
	
	public Timesheet getTimesheet(TimesheetUser user, Date day);
	
	public void persist(Object t) throws Exception;

	public void remove(Object t) throws Exception;
}
