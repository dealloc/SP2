package be.ehb.spg3.entities.groups;

import javax.persistence.Column;
import javax.persistence.Entity;

// Created by Wannes Gennar. All rights reserved
@Entity
public class Group
{
	@Column
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
