package org.nishen.timesheet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "leave_type")
public class LeaveType implements Serializable
{
	private static final long serialVersionUID = -3529408213552788136L;
	
	private static final Logger log = LoggerFactory.getLogger(LeaveType.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "default_hours")
	private int defaultHours;

	public LeaveType()
	{
		log.trace("instantiating class: {}", this.getClass().getName());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getDefaultHours()
	{
		return defaultHours;
	}

	public void setDefaultHours(int defaultHours)
	{
		this.defaultHours = defaultHours;
	}
}
