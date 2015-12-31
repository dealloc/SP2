package be.ehb.spg3.entities.groups;

import be.ehb.spg3.entities.BaseEntity;

import javax.persistence.*;

// Created by Wannes Gennar. All rights reserved

/**
 * Each user belongs to a "group" which can be a company or just a logical grouping.
 */
@Entity
@Table(name = "groups")
public class Group extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;

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
