package be.ehb.spg3.entities.groups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// Created by Wannes Gennar. All rights reserved
@Entity
@Table(name = "groups")
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
