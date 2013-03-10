package org.nishen.timesheet.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	public static Date getDate(int year, int month, int day)
	{
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);

		return c.getTime();
	}
	
	public static Date addDays(Date date, int duration)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, duration);
		
		return c.getTime();
	}
}
