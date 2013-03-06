package org.nishen.timesheet.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "timesheet_day")
public class TimesheetDay implements Serializable
{
	private static final long serialVersionUID = -5518475651241183893L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "timesheet_id")
	private Timesheet timesheet;

	@Temporal(TemporalType.DATE)
	@Column(name = "timesheet_day")
	private Date timesheetDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "planned_start")
	private Date plannedStart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "planned_finish")
	private Date plannedFinish;

	@Column(name = "planned_lunch")
	private long plannedLunch;

	@Column(name = "planned_leave")
	private long plannedLeave;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="planned_leave_type_id")
	private LeaveType plannedLeaveType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_start")
	private Date actualStart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_finish")
	private Date actualFinish;

	@Column(name = "actual_lunch")
	private long actualLunch;

	@Column(name = "actual_leave")
	private long actualLeave;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="planned_leave_type_id")
	private LeaveType actualLeaveType;
	
	public TimesheetDay()
	{}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Timesheet getTimesheet()
	{
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet)
	{
		this.timesheet = timesheet;
	}

	public Date getTimesheetDay()
	{
		return timesheetDay;
	}

	public void setTimesheetDay(Date timesheetDay)
	{
		this.timesheetDay = timesheetDay;
	}

	public Date getPlannedStart()
	{
		return plannedStart;
	}

	public void setPlannedStart(Date plannedStart)
	{
		this.plannedStart = plannedStart;
	}

	public Date getPlannedFinish()
	{
		return plannedFinish;
	}

	public void setPlannedFinish(Date plannedFinish)
	{
		this.plannedFinish = plannedFinish;
	}

	public long getPlannedLunch()
	{
		return plannedLunch;
	}

	public void setPlannedLunch(long plannedLunch)
	{
		this.plannedLunch = plannedLunch;
	}

	public long getPlannedLeave()
	{
		return plannedLeave;
	}

	public void setPlannedLeave(long plannedLeave)
	{
		this.plannedLeave = plannedLeave;
	}

	public Date getActualStart()
	{
		return actualStart;
	}

	public void setActualStart(Date actualStart)
	{
		this.actualStart = actualStart;
	}

	public Date getActualFinish()
	{
		return actualFinish;
	}

	public void setActualFinish(Date actualFinish)
	{
		this.actualFinish = actualFinish;
	}

	public long getActualLunch()
	{
		return actualLunch;
	}

	public void setActualLunch(long actualLunch)
	{
		this.actualLunch = actualLunch;
	}

	public long getActualLeave()
	{
		return actualLeave;
	}

	public void setActualLeave(long actualLeave)
	{
		this.actualLeave = actualLeave;
	}

	public LeaveType getPlannedLeaveType()
	{
		return plannedLeaveType;
	}

	public void setPlannedLeaveType(LeaveType plannedLeaveType)
	{
		this.plannedLeaveType = plannedLeaveType;
	}

	public LeaveType getActualLeaveType()
	{
		return actualLeaveType;
	}

	public void setActualLeaveType(LeaveType actualLeaveType)
	{
		this.actualLeaveType = actualLeaveType;
	}
}
