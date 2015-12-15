package be.ehb.spg3.entities.groups;

import com.j256.ormlite.field.DatabaseField;

// Created by Wannes Gennar. All rights reserved
public class Group
{
	@DatabaseField
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
