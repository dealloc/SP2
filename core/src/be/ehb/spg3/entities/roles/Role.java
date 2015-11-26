package be.ehb.spg3.entities.roles;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.permissions.Permission;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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
	@DatabaseField
	private String name;
	@ForeignCollectionField
	private ForeignCollection<Permission> permissions;

	public Role()
	{
	}

	/**
	 * Create a new named role.
	 *
	 * @param name        The name of this role.
	 * @param permissions The permissions for this role.
	 */
	public Role(String name, ForeignCollection<Permission> permissions)
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
	public ForeignCollection<Permission> getPermissions()
	{
		return permissions;
	}

	/**
	 * Set the permissions for this role.
	 *
	 * @param permissions The new permissions for this role.
	 * @see Permission
	 */
	public void setPermissions(ForeignCollection<Permission> permissions)
	{
		this.permissions = permissions;
	}
}
