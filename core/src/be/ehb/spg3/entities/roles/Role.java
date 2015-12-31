package be.ehb.spg3.entities.roles;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.BaseEntity;
import be.ehb.spg3.entities.permissions.Permission;

import javax.persistence.*;
import java.util.Collection;

/**
 * A role represents a set of permissions a user can have.
 * It basicly describes the capabilities of a user within the program
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@ManyToMany
	private Collection<Permission> permissions;

	public Role()
	{
	}

	/**
	 * Create a new named role.
	 *
	 * @param name        The name of this role.
	 * @param permissions The permissions for this role.
	 */
	public Role(String name, Collection<Permission> permissions)
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
	public Collection<Permission> getPermissions()
	{
		return permissions;
	}

	/**
	 * Set the permissions for this role.
	 *
	 * @param permissions The new permissions for this role.
	 * @see Permission
	 */
	public void setPermissions(Collection<Permission> permissions)
	{
		this.permissions = permissions;
	}
}
