package be.ehb.spg3.models;

// Created by Wannes Gennar. All rights reserved

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * TODO change to javax.sql annotations to remove coupling!
 */

/**
 * A role represents a set of permissions a user can have.
 * It basicly describes the capabilities of a user within the program
 */
@DatabaseTable(tableName = "roles")
public class Role
{

	@DatabaseField(id = true)
	private int id;
	private String name;
	private List<Permission> permissions;

	public Role()
	{
	}

	/**
	 * Create a new named role.
	 *
	 * @param name        The name of this role.
	 * @param permissions The permissions for this role.
	 */
	public Role(String name, List<Permission> permissions)
	{
		this.name = name;
		this.permissions = permissions;
	}

	/**
	 * Get the name of this role.
	 *
	 * @return the name of this role.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Give this role a new name.
	 *
	 * @param name the new name of this role.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the permissions for this role.
	 *
	 * @return This role's permissions.
	 * @see Permission
	 */
	public List<Permission> getPermissions()
	{
		return permissions;
	}

	/**
	 * Set the permissions for this role.
	 *
	 * @param permissions The new permissions for this role.
	 * @see Permission
	 */
	public void setPermissions(List<Permission> permissions)
	{
		this.permissions = permissions;
	}
}
