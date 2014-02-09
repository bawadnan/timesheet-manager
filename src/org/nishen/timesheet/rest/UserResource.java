package org.nishen.timesheet.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.nishen.timesheet.dao.TimesheetDAO;
import org.nishen.timesheet.entity.TimesheetUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/user/{oneId}")
public class UserResource
{
	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private TimesheetDAO timesheetDAO;

	public UserResource()
	{
		log.debug("UserResource instantiated");
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public TimesheetUser getUser(@PathParam("oneId") String oneId)
	{
		TimesheetUser u = null;

		if (timesheetDAO == null)
		{
			log.warn("timesheetDAO is null...");
			u = new TimesheetUser();
		}
		else
		{
			u = timesheetDAO.getUser(oneId);
		}

		return u;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void createUser(TimesheetUser user)
	{
		log.debug("action:{}", "addUser");
		log.debug("id:    {}", user.getId());
		log.debug("oneId: {}", user.getOneId());
		log.debug("email: {}", user.getEmail());
		
		try
		{
			user.setId(0);
			timesheetDAO.persist(user);
		}
		catch (Exception e)
		{
			log.error("unable to create user");
			log.debug("unable to create user:", e);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void updateUser(TimesheetUser user)
	{
		log.debug("action:{}", "updateUser");
		log.debug("id:    {}", user.getId());
		log.debug("oneId: {}", user.getOneId());
		log.debug("email: {}", user.getEmail());
		
		try
		{
			timesheetDAO.update(user);
		}
		catch (Exception e)
		{
			log.error("unable to update user");
			log.debug("unable to update user:", e);
		}
	}
	
	@DELETE
	public void deleteUser(@PathParam("oneId") String oneId)
	{
		TimesheetUser user = timesheetDAO.getUser(oneId);
		
		log.debug("action:{}", "deleteUser");
		log.debug("id:    {}", user.getId());
		log.debug("oneId: {}", user.getOneId());
		log.debug("email: {}", user.getEmail());

		try
		{
			timesheetDAO.remove(user);
		}
		catch (Exception e)
		{
			log.error("unable to delete user");
			log.debug("unable to delete user:", e);
		}
	}
}
