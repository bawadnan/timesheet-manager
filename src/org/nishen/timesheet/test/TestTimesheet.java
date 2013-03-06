package org.nishen.timesheet.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nishen.timesheet.entity.Timesheet;
import org.nishen.timesheet.entity.TimesheetDay;
import org.nishen.timesheet.entity.TimesheetUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTimesheet
{
	private static final Logger log = LoggerFactory.getLogger(TestUser.class);
	
	private static final String PERSISTENCE_UNIT = "timesheet";

	private static final int DURATION = 14;
	
	private static EntityManager em = null;
	
	private static TimesheetUser tu = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		log.trace("persistence manager created");
		
		tu = new TimesheetUser();
		tu.setName("Test User");
		tu.setDepartment("Test Department");
		tu.setEmail("test@email.com");
		tu.setOneId("1234567890");
		tu.setStatus("ACTIVE");
		
		em.getTransaction().begin();
		em.persist(tu);
		em.getTransaction().commit();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		em.close();
	}

	@Test
	public void testTimesheet()
	{
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(2013, Calendar.MARCH, 14);

		Timesheet t = new Timesheet();
		t.setTimesheetUser(tu);
		t.setDuration(DURATION);
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
			
			td.setPlannedLunch(30*60*1000);
			td.setPlannedLeave(0);
			
			timesheetDays.add(td);
		}

		t.setTimesheetDays(timesheetDays);
		
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}
}
