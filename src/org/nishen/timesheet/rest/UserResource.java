package org.nishen.timesheet.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.nishen.timesheet.dao.TimesheetDAO;
import org.nishen.timesheet.entity.TimesheetUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/user/{oneId}")
@Component
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
	@Produces(MediaType.APPLICATION_XML)
	public TimesheetUser getUser(@PathParam("oneId") String oneId)
	{
		TimesheetUser u = null;
		
		if (timesheetDAO == null)
		{
			log.warn("timesheetDAO is null...");
			u =  new TimesheetUser();
		}
		else
		{
			u = timesheetDAO.getUser(oneId);
		}
		
		return u;
	}
}
