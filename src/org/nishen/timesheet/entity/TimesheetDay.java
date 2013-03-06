package org.nishen.timesheet.entity;

import java.sql.Time;
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
public class TimesheetDay
{
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

	@Temporal(TemporalType.TIME)
	@Column(name = "planned_start")
	private Time plannedStart;

	@Temporal(TemporalType.TIME)
	@Column(name = "planned_finish")
	private Time plannedFinish;

	@Temporal(TemporalType.TIME)
	@Column(name = "planned_lunch")
	private Time plannedLunch;

	@Temporal(TemporalType.TIME)
	@Column(name = "planned_leave")
	private Time plannedLeave;

	// TODO: sort out planned leave type.
	// private LeaveType planned_leave_type_id;

	@Temporal(TemporalType.TIME)
	@Column(name = "actual_start")
	private Time actualStart;

	@Temporal(TemporalType.TIME)
	@Column(name = "actual_finish")
	private Time actualFinish;

	@Temporal(TemporalType.TIME)
	@Column(name = "actual_lunch")
	private Time actualLunch;

	@Temporal(TemporalType.TIME)
	@Column(name = "actual_leave")
	private Time actualLeave;

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

	public Time getPlannedStart()
	{
		return plannedStart;
	}

	public void setPlannedStart(Time plannedStart)
	{
		this.plannedStart = plannedStart;
	}

	public Time getPlannedFinish()
	{
		return plannedFinish;
	}

	public void setPlannedFinish(Time plannedFinish)
	{
		this.plannedFinish = plannedFinish;
	}

	public Time getPlannedLunch()
	{
		return plannedLunch;
	}

	public void setPlannedLunch(Time plannedLunch)
	{
		this.plannedLunch = plannedLunch;
	}

	public Time getPlannedLeave()
	{
		return plannedLeave;
	}

	public void setPlannedLeave(Time plannedLeave)
	{
		this.plannedLeave = plannedLeave;
	}

	public Time getActualStart()
	{
		return actualStart;
	}

	public void setActualStart(Time actualStart)
	{
		this.actualStart = actualStart;
	}

	public Time getActualFinish()
	{
		return actualFinish;
	}

	public void setActualFinish(Time actualFinish)
	{
		this.actualFinish = actualFinish;
	}

	public Time getActualLunch()
	{
		return actualLunch;
	}

	public void setActualLunch(Time actualLunch)
	{
		this.actualLunch = actualLunch;
	}

	public Time getActualLeave()
	{
		return actualLeave;
	}

	public void setActualLeave(Time actualLeave)
	{
		this.actualLeave = actualLeave;
	}

	// TODO: sort out planned leave type.
	// private LeaveType actual_leave_type_id;
}
