package org.nishen.timesheet.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nishen.timesheet.entity.TimesheetUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUser
{
	private static final Logger log = LoggerFactory.getLogger(TestUser.class);
	
	private static final String PERSISTENCE_UNIT = "timesheet";
	
	private static EntityManager em = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
		log.trace("persistence manager created");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		String sql = "delete from User u where lower(u.name) like :name";
		em.getTransaction().begin();
		Query query = em.createQuery(sql).setParameter("name", "test%");
		query.executeUpdate();
		em.flush();
		em.getTransaction().commit();
		
		em.close();
	}

	@Test
	public void testUserOperations()
	{
		TimesheetUser u = new TimesheetUser();
		u.setName("Test User");
		u.setDepartment("Test Department");
		u.setEmail("test@email.com");
		u.setOneId("1234567890");
		u.setStatus("ACTIVE");
		
		TimesheetUser u1 = new TimesheetUser();
		u1.setId(u.getId());
		u1.setOneId(u.getOneId());
		u1.setName(u.getName());
		u1.setEmail(u.getEmail());
		u1.setDepartment(u.getDepartment());
		u1.setStatus(u.getStatus());

		// create
		em.getTransaction().begin();
		em.persist(u1);
		em.flush();
		em.getTransaction().commit();
		
		int id = u1.getId();
		u.setId(id);
		Assert.assertTrue("invalid id returned", id > 0);
		
		// read
		TimesheetUser u2 = em.find(TimesheetUser.class, new Long(id));

		Assert.assertEquals("objects do not match", u, u2);

		// update
		u2.setEmail("other@email.com");
		em.getTransaction().begin();
		em.persist(u2);
		em.flush();
		em.getTransaction().commit();

		Assert.assertTrue("objects are not different", !u.equals(u2));
		
		// delete
		em.getTransaction().begin();
		em.remove(u2);
		em.getTransaction().commit();
		
		Assert.assertNull(em.find(TimesheetUser.class, new Long(id)));
	}
}
