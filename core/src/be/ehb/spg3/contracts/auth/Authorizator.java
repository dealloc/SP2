package be.ehb.spg3.contracts.auth;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.models.Permission;
import be.ehb.spg3.models.User;

/**
 * Provides a contract of how an authorization module should behave.
 */
public interface Authorizator
{
	/**
	 * Check if the currently authorized user has the given permission.
	 *
	 * @param permission The permission to check.
	 * @return true if the user has this permission, false if not or if no user is authenticated.
	 */
	boolean can(String permission);

	/**
	 * Check if a user doesn't have given permission.
	 *
	 * @param permission The permission to check.
	 * @return true if the user doesn't have this permission or no user is authenticated,
	 * false if the user has this permission.
	 */
	boolean cannot(String permission);

	/**
	 * Grants a permission to a user.
	 *
	 * @param subject    The user to grant the permission to.
	 * @param permission The permission to grant.
	 */
	void grant(User subject, Permission permission);

	/**
	 * Revoke a permission from a user.
	 *
	 * @param subject    The user to revoke the permission from.
	 * @param permission The permission to revoke.
	 * @apiNote This function has no effect if the user doesn't have given permission.
	 */
	void revoke(User subject, Permission permission);
}
