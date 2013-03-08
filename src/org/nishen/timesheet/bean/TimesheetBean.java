package org.nishen.timesheet.bean;

import org.nishen.timesheet.dao.TimesheetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimesheetBean
{
	private static final Logger log = LoggerFactory.getLogger(TestBean.class);

	private TimesheetDAO timesheetDAO;

	public void setTimesheetDAO(TimesheetDAO timesheetDAO)
	{
		this.timesheetDAO = timesheetDAO;
	}
}
