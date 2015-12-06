package be.ehb.spg3.auth;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.exceptions.ConnectivityException;
import be.ehb.spg3.exceptions.QueryException;

import java.util.List;

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
	private final UserRepository repository;
	private User user = null;

	public AuthRepository()
	{
		this.repository = resolve(UserRepository.class);
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
			password = resolve(Encryptor.class).encrypt(password);
			List<User> users = this.repository.findByFields(new String[]{"username", username}, new String[]{"password", password});
			if (!users.isEmpty())
			{
				this.user = users.get(0);
				return true;
			}
		}
		catch (QueryException | ConnectivityException e)
		{
			e.printStackTrace(); // TODO handle exeption
		}

		return false;
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
		return false;
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
		return true;
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
		//TODO implement grant function
	}

	/**
	 * Revoke a permission from a user.
	 *
	 * @param subject    The user to revoke the permission from.
	 * @param permission The permission to revoke.
	 * @apiNote This function has no effect if the user doesn't have given permission.
	 */
	@Override
	public void revoke(User subject, Permission permission)
	{
		//TODO implement revoke function
	}
}
