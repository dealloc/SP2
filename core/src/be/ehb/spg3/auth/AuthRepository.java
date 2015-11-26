package be.ehb.spg3.auth;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.users.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

// Created by Wannes Gennar. All rights reserved

/**
 * The authrepository is the central class managing the authentication and authorization processing in the application.
 *
 * @see be.ehb.spg3.contracts.auth.Authenticator
 * @see be.ehb.spg3.contracts.auth.Authorizator
 */
public class AuthRepository implements Authenticator, Authorizator
{
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
		//We create a token with username and password based on Shiro's AuthenticationToken interface
		UsernamePasswordToken credentials = new UsernamePasswordToken(username, password);
		/*
		* Shiro remembers the user identity if they return to the application at a later date
		* credentials.setRememberMe(true);
		*/
		//It will acquire the currently executing user via the getsubject() method call.
		Subject currentUser = SecurityUtils.getSubject();
		//Handling success or failure
		try {
			//The credentials are been saved in a token, now we need to submit this token to Shiro
			currentUser.login(credentials);
			return true;
		} catch (UnknownAccountException uae) {
			System.out.println("Dit account bestaat niet!");

		} catch (IncorrectCredentialsException ice) {
			System.out.println("Ongeldige gebruikersnaam of wachtwoord!");

		} catch (LockedAccountException lae) {
			System.out.println("Dit account werd onlangs afgesloten!");

		} catch (ExcessiveAttemptsException eae) {
			System.out.println("Te veel foutieve aanmeldpogingen!");
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
		//It will acquire the currently executing user via the getsubject() method call.
		Subject currentUser = SecurityUtils.getSubject();
		//If the current user is authenticated then show the principals else return null
		if (currentUser.isAuthenticated()) {
			System.out.println(currentUser.getPrincipal());
			return (User) currentUser; //TODO create constructor for user with subject
		} else {
			System.out.println("Not authenticated!");
			return null;
		}
	}

	/**
	 * Log out the currently authenticated user.
	 * Does nothing if no user is currently authenticated.
	 */
	@Override
	public void logout()
	{
		//It will acquire the currently executing user via the getsubject() method call.
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
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
		//It will acquire the currently executing user via the getsubject() method call.
		Subject currentUser = SecurityUtils.getSubject();
		//Check if the current user is authenticated
		if (currentUser.isAuthenticated()) {
			System.out.println("The current user is authenticated!");
			//Check if the user has the given permission
			if (currentUser.isPermitted(permission)) {
				System.out.println("The current user has the given permission!");
				return true;
			}
		}
		System.out.println("The current user isn't authenticated or doesn't have the given permission!");
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
		//It will acquire the currently executing user via the getsubject() method call.
		Subject currentUser = SecurityUtils.getSubject();
		//Check if the current user is authenticated
		if (currentUser.isAuthenticated()) {
			System.out.println("The current user is authenticated!");
			//Check if the user doesn't have the given permission
			if (currentUser.isPermitted(permission)) {
				System.out.println("The current user has the given permission!");
				return false;
			}
		}
		System.out.println("The current user isn't authenticated or doesn't have the given permission!");
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
