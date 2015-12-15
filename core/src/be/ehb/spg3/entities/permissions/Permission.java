package be.ehb.spg3.entities.permissions;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.roles.Role;

import javax.persistence.*;

@Entity
public class Permission
{
	@Column
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	@ManyToMany
	private Role role;

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
