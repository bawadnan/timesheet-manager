package org.nishen.timesheet.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.nishen.timesheet.dao.TimesheetDAO;
import org.nishen.timesheet.entity.Timesheet;
import org.nishen.timesheet.entity.TimesheetDay;
import org.nishen.timesheet.entity.TimesheetUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean
{
	private static final Logger log = LoggerFactory.getLogger(TestBean.class);

	private TimesheetDAO timesheetDAO;

	private String name;
	private int age;

	public TestBean()
	{
		log.trace("instantiating class: {}", this.getClass().getName());

		name = "Nishen";
		age = 37;
	}

	public String addTimesheet()
	{
		TimesheetUser tu = new TimesheetUser();
		tu.setOneId("TU1234567890");
		tu.setEmail("testDepartment@email.com");
		tu.setName("Test User");
		tu.setDepartment("Test Department");
		tu.setStatus("ACTIVE");

		try
		{
			timesheetDAO.persist(tu);
		}
		catch (Exception e)
		{
			log.error("failed to persist timesheet user: {}", tu.getId());
			log.debug("exception:\n{}", e);
			return Result.FAILURE;
		}

		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(2013, Calendar.MARCH, 14);

		Timesheet t = new Timesheet();
		tu.setTimesheets(Arrays.asList(t));
		t.setTimesheetUser(tu);
		t.setDuration(7);
		t.setStatus("ACTIVE");
		t.setPeriodCommenceDate(c.getTime());

		List<TimesheetDay> timesheetDays = new ArrayList<TimesheetDay>();
		for (int x = t.getDuration(); x > 0; x--)
		{
			c.add(Calendar.DAY_OF_MONTH, 1);
			TimesheetDay td = new TimesheetDay();
			td.setTimesheet(t);
			td.setTimesheetDay(c.getTime());

			Calendar ct = Calendar.getInstance();
			ct.clear();
			ct.setTime(c.getTime());

			ct.set(Calendar.HOUR, 9);
			ct.set(Calendar.MINUTE, 0);
			ct.set(Calendar.SECOND, 0);
			td.setPlannedStart(ct.getTime());

			ct.set(Calendar.HOUR, 17);
			ct.set(Calendar.MINUTE, 0);
			ct.set(Calendar.SECOND, 0);
			td.setPlannedFinish(ct.getTime());

			td.setPlannedLunch(30 * 60 * 1000);
			td.setPlannedLeave(0);

			timesheetDays.add(td);
		}

		t.setTimesheetDays(timesheetDays);

		try
		{
			timesheetDAO.persist(t);
		}
		catch (Exception e)
		{
			log.error("failed to persist timesheet: {}", t.getId());
			log.debug("exception:\n{}", e);
			return Result.FAILURE;
		}

		return Result.SUCCESS;
	}

	public String update()
	{
		log.debug("age: {}", age);
		age += 1;

		return Result.SUCCESS;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setTimesheetDAO(TimesheetDAO timesheetDAO)
	{
		this.timesheetDAO = timesheetDAO;
	}
}
