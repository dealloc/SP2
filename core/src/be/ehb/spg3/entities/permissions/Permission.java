package be.ehb.spg3.entities.permissions;

// Created by Wannes Gennar. All rights reserved

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission
{
	@Column
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;

	public Permission()
	{
	}

	public Permission(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
