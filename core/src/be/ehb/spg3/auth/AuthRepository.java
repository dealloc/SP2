package be.ehb.spg3.auth;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.contracts.encryption.Hasher;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.errors.ErrorEvent;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved

/**
 * The authrepository is the central class managing the authentication and authorization processing in the application.
 *
 * @see be.ehb.spg3.contracts.auth.Authenticator
 * @see be.ehb.spg3.contracts.auth.Authorizator
 */
public class AuthRepository implements Authenticator, Authorizator
{
	private UserRepository repository;
	private User user = null;

	public AuthRepository()
	{
		this.repository = null;
	}

	private UserRepository getRepository()
	{
		if (this.repository == null)
			this.repository = resolve(UserRepository.class);

		return this.repository;
	}

	/**
	 * Attempt to log a user using given credentials.
	 *
	 * @param username The username which will be used to authenticate.
	 * @param password The password which willl be used to authenticate.
	 * @return true if the attempt was successfull, false if it was not.
	 */
	@Override
	public boolean login(String username, String password)
	{
		try
		{
			password = resolve(Hasher.class).hash(password);

			List<User> users = this.getRepository().findByField("username", username);
			if (!users.isEmpty() && users.get(0).getPassword().equals(password))
			{
				this.user = users.get(0);
				return true;
			}
		}
		catch (SQLException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}

		return false;
	}

	/**
	 * Log in as given the user.
	 *
	 * @param user The user to log in.
	 */
	@Override
	public void sudo(User user)
	{
		this.user = user;
	}

	/**
	 * Get the currently authenticated user.
	 *
	 * @return The authenticated User or null if none is authenticated.
	 * @see User
	 */
	@Override
	public User auth()
	{
		return this.user;
	}

	/**
	 * Log out the currently authenticated user.
	 * Does nothing if no user is currently authenticated.
	 */
	@Override
	public void logout()
	{
		this.user = null;
	}

	/**
	 * Check if the currently authorized user has the given permission.
	 *
	 * @param permission The permission to check.
	 * @return true if the user has this permission, false if not or if no user is authenticated.
	 */
	@Override
	public boolean can(String permission)
	{
		return this.auth() != null && this.auth().getRole() != null && this.auth().getRole().getPermissions().parallelStream().filter(p -> Pattern.compile(p.getName()).matcher(permission).groupCount() == 0).count() != 0;
	}

	/**
	 * Check if a user doesn't have given permission.
	 *
	 * @param permission The permission to check.
	 * @return true if the user doesn't have this permission or no user is authenticated,
	 * false if the user has this permission.
	 */
	@Override
	public boolean cannot(String permission)
	{
		return !this.can(permission);
	}

	/**
	 * Grants a permission to a user.
	 *
	 * @param subject    The user to grant the permission to.
	 * @param permission The permission to grant.
	 */
	@Override
	public void grant(User subject, Permission permission)
	{
		//TODO figure out how to implement since we're using role based permissions
	}

	/**
	 * Revoke a permission from a user.
	 * <br>
	 * <p>This function has no effect if the user doesn't have given permission.</p>
	 * @param subject    The user to revoke the permission from.
	 * @param permission The permission to revoke.
	 */
	@Override
	public void revoke(User subject, Permission permission)
	{
		//TODO figure out how to implement since we're using role based permissions
	}
}
