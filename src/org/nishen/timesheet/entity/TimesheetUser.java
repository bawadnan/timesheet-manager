package org.nishen.timesheet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "timesheet_user")
public class TimesheetUser implements Serializable
{
	private static final long serialVersionUID = 4821049904009561911L;

	private static final Logger log = LoggerFactory.getLogger(TimesheetUser.class);

	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement
	@Column(name = "one_id")
	private String oneId;

	@XmlElement
	@Column(name = "email")
	private String email;

	@XmlElement
	@Column(name = "name")
	private String name;

	@XmlElement
	@Column(name = "department")
	private String department;

	@XmlElement
	@Column(name = "status")
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "timesheetUser")
	@OrderBy("periodCommenceDate DESC")
	private List<Timesheet> timesheets;

	public TimesheetUser()
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

	public String getOneId()
	{
		return oneId;
	}

	public void setOneId(String oneId)
	{
		this.oneId = oneId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<Timesheet> getTimesheets()
	{
		return timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets)
	{
		this.timesheets = timesheets;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oneId == null) ? 0 : oneId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		TimesheetUser other = (TimesheetUser) obj;
		if (department == null)
		{
			if (other.department != null)
			{
				return false;
			}
		}
		else if (!department.equals(other.department))
		{
			return false;
		}
		if (email == null)
		{
			if (other.email != null)
			{
				return false;
			}
		}
		else if (!email.equals(other.email))
		{
			return false;
		}
		if (id != other.id)
		{
			return false;
		}
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(other.name))
		{
			return false;
		}
		if (oneId == null)
		{
			if (other.oneId != null)
			{
				return false;
			}
		}
		else if (!oneId.equals(other.oneId))
		{
			return false;
		}
		if (status == null)
		{
			if (other.status != null)
			{
				return false;
			}
		}
		else if (!status.equals(other.status))
		{
			return false;
		}
		return true;
	}
}
