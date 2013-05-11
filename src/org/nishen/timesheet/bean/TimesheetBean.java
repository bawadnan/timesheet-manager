package org.nishen.timesheet.bean;

import org.nishen.timesheet.dao.TimesheetDAO;
import org.nishen.timesheet.entity.Timesheet;
import org.nishen.timesheet.entity.TimesheetDay;
import org.nishen.timesheet.entity.TimesheetUser;
import org.nishen.timesheet.util.DateUtil;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimesheetBean
{
	private static final Logger log = LoggerFactory.getLogger(TestBean.class);

	private TimesheetDAO timesheetDAO;

	private Timesheet timesheet;
	
	private TimesheetUser user;

	public TimesheetBean()
	{}

	public TimesheetBean(TimesheetDAO timesheetDAO)
	{
		log.trace("instantiating class: {}", this.getClass().getName());
		
		this.timesheetDAO = timesheetDAO;

		user = new TimesheetUser();
		user.setId(1);
		user.setOneId("mq97000313");
		user.setName("Nishen Naidoo");
		user.setEmail("nishen.naidoo@mq.edu.au");
		user.setStatus("ACTIVE");
		
		timesheet = this.timesheetDAO.getTimesheet(user, DateUtil.getDate(2013,  3,  14));
	}
	
	public String updateTimesheet()
	{
		log.debug("performed action: {}", "updateTimesheet");

		return Result.SUCCESS;
	}

	public void onEdit(RowEditEvent event)
	{
		TimesheetDay td = (TimesheetDay) event.getObject();
		log.debug("edit event: {}", td.getPlannedStart());
		log.debug("edit event: {}", td.getPlannedStart().getTime());
	}
	
	public void onCancel(RowEditEvent event)
	{
		TimesheetDay td = (TimesheetDay) event.getObject();
		log.debug("cancel event: {}", td.getPlannedStart());
		log.debug("cancel event: {}", td.getPlannedStart().getTime());
	}

	public Timesheet getTimesheet()
	{
		return timesheet;
	}

	public void setTimesheet(Timesheet t)
	{
		this.timesheet = t;
	}
}
