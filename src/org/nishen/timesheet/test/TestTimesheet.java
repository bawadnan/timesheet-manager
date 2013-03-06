package org.nishen.timesheet.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTimesheet
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
		String sql = "delete from Timesheet t";
		em.getTransaction().begin();
		Query query = em.createQuery(sql);
		query.executeUpdate();
		em.flush();
		em.getTransaction().commit();
		
		em.close();
	}

	@Test
	public void test()
	{
		Assert.fail("Not yet implemented");
	}
}
