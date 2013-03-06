package org.nishen.timesheet.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "timesheet")
public class Timesheet implements Serializable
{
	private static final long serialVersionUID = -3741953535615062629L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "period_commence_date")
	private Date periodCommenceDate;

	@Column(name = "duration")
	private int duration;

	// timesheet status: NEW, PLANNED_SUBMITTED, PLANNED_APPROVED,
	// ACTUAL_SUBMITTED, ACTUAL_APPROVED
	@Column(name = "status")
	private String status;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "timesheet_user_id", nullable = false, insertable = false, updatable = false)
	private TimesheetUser user;

	@OneToMany (fetch = FetchType.EAGER, mappedBy = "timesheet")
	@OrderBy("timesheetDay")
	List<TimesheetDay> timesheetDays;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getPeriodCommenceDate()
	{
		return periodCommenceDate;
	}

	public void setPeriodCommenceDate(Date periodCommenceDate)
	{
		this.periodCommenceDate = periodCommenceDate;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public TimesheetUser getUser()
	{
		return user;
	}

	public void setUser(TimesheetUser user)
	{
		this.user = user;
	}
}
