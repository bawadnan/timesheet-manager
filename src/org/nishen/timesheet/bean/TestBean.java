package org.nishen.timesheet.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean
{
	private static final Logger log = LoggerFactory.getLogger(TestBean.class);

	private String name;
	private int age;

	public TestBean()
	{
		log.debug("instantiating class: {}", this.getClass().getName());

		name = "Nishen";
		age = 37;
	}

	public String update()
	{
		log.debug("age: {}", age);
		age += 1;

		return Result.SUCCESS;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
}
