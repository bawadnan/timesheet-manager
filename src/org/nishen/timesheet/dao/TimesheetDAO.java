package org.nishen.timesheet.dao;


public interface TimesheetDAO
{
	public void persist(Object t) throws Exception;
	public void remove(Object t) throws Exception;
}
