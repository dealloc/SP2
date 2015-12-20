package be.ehb.spg3.entities.groups;

import javax.persistence.*;

// Created by Wannes Gennar. All rights reserved
@Entity
@Table(name = "groups")
public class Group
{
	@Column
	@Id
	@GeneratedValue
	private int id;

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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
