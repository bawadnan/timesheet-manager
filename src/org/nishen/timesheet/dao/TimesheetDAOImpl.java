package org.nishen.timesheet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimesheetDAOImpl implements TimesheetDAO
{
	private static final Logger log = LoggerFactory.getLogger(TimesheetDAOImpl.class);

	private EntityManagerFactory timesheetEMF;

	public TimesheetDAOImpl()
	{
		log.trace("instantiating class: {}", this.getClass().getName());
	}
	
	public void persist(Object t) throws Exception
	{
		EntityManager em = timesheetEMF.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		}
		finally
		{
			if (em != null)
				em.close();
		}
	}

	public void remove(Object t) throws Exception
	{
		EntityManager em = timesheetEMF.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();
		}
		finally
		{
			if (em != null)
				em.close();
		}
	}

	public void setTimesheetEMF(EntityManagerFactory timesheetEMF)
	{
		this.timesheetEMF = timesheetEMF;
	}
}
