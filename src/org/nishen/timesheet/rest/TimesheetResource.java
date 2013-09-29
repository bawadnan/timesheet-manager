package org.nishen.timesheet.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/timesheet/{id}")
public class TimesheetResource
{
	@GET
	@Produces("text/xml")
	public String getTimesheet(@PathParam("id") String id)
	{
		return String.format("<timesheet>%s</timesheet>", id);
	}
}
