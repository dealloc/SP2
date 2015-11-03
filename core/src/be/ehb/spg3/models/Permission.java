package be.ehb.spg3.models;

// Created by Wannes Gennar. All rights reserved

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * TODO change to javax.sql annotations to remove coupling!
 */
@DatabaseTable(tableName = "permissions")
public class Permission
{
	@DatabaseField(id = true) private int id;
	@DatabaseField private String name;

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
