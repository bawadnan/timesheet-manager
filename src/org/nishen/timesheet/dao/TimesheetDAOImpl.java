package org.nishen.timesheet.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.nishen.timesheet.entity.Timesheet;
import org.nishen.timesheet.entity.TimesheetDay;
import org.nishen.timesheet.entity.TimesheetUser;
import org.nishen.timesheet.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimesheetDAOImpl implements TimesheetDAO
{
	private static final Logger log = LoggerFactory.getLogger(TimesheetDAOImpl.class);

	private EntityManagerFactory timesheetEMF;

	public TimesheetDAOImpl()
	{
		log.debug("instantiating class: {}", this.getClass().getName());
	}

	public TimesheetUser getUser(String oneId)
	{
		TimesheetUser u = null;

		String sql = "";
		sql += "select tu from TimesheetUser tu ";
		sql += " where tu.oneId = :oneId ";

		EntityManager em = timesheetEMF.createEntityManager();
		try
		{
			Query query = em.createQuery(sql);
			query.setParameter("oneId", oneId);
			u = (TimesheetUser) query.getSingleResult();
		}
		catch (NoResultException nre)
		{
			log.debug("user not found: {}", oneId);
		}
		finally
		{
			if (em != null)
				em.close();
		}

		return u;
	}

	public Timesheet getTimesheet(TimesheetUser user, Date day)
	{
		Timesheet t = null;

		String sql = "";
		sql += "select t from Timesheet t ";
		sql += " where t.timesheetUser = :user ";
		sql += "   and t.periodCommenceDate = :day";

		EntityManager em = timesheetEMF.createEntityManager();
		try
		{
			Query query = em.createQuery(sql);
			query.setParameter("user", user);
			query.setParameter("day", day);
			t = (Timesheet) query.getSingleResult();
		}
		catch (NoResultException nre)
		{
			log.debug("timesheet not found [{}]: {}", user.getId(), day);
		}
		finally
		{
			if (em != null)
				em.close();
		}

		if (t == null)
		{
			log.debug("generating timesheet");
			t = makeTimesheet(user, day);
		}

		return t;
	}

	private Timesheet makeTimesheet(TimesheetUser user, Date day)
	{
		Timesheet t = new Timesheet();
		t.setTimesheetUser(user);
		t.setPeriodCommenceDate(day);
		t.setDuration(14);
		t.setStatus("ACTIVE");

		List<TimesheetDay> td = new ArrayList<TimesheetDay>();
		Calendar zero = Calendar.getInstance();
		zero.setTimeInMillis(0);
		Date dayvar = day;
		for (int x = 0; x < t.getDuration(); x++)
		{
			TimesheetDay tday = new TimesheetDay();
			tday.setTimesheetDay(dayvar);
			dayvar = DateUtil.addDays(dayvar, 1);
			tday.setPlannedStart(zero.getTime());
			tday.setPlannedFinish(zero.getTime());
			tday.setPlannedLunch(0);
			tday.setPlannedLeave(0);

			tday.setActualStart(zero.getTime());
			tday.setActualFinish(zero.getTime());
			tday.setActualLunch(0);
			tday.setActualLeave(0);

			td.add(tday);
		}
		t.setTimesheetDays(td);

		return t;
	}

	public void persist(Object t) throws Exception
	{
		EntityManager em = timesheetEMF.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(t);
		et.commit();
	}

	public void update(Object t) throws Exception
	{
		EntityManager em = timesheetEMF.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		t = em.merge(t);
		em.persist(t);
		et.commit();
	}

	public void remove(Object t) throws Exception
	{
		EntityManager em = timesheetEMF.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		t = em.merge(t);
		em.remove(t);
		et.commit();
	}

	public void setTimesheetEMF(EntityManagerFactory timesheetEMF)
	{
		this.timesheetEMF = timesheetEMF;
	}
}
