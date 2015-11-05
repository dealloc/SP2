package be.ehb.spg3.entities.users;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.roles.Role;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * TODO change to javax.sql annotations to remove coupling!
 */

/**
 * Represents a (human) user that interacts with the system.
 * This includes authentication data and identification data.
 */
@DatabaseTable(tableName = "users")
public class User
{
	@DatabaseField(id = true) private int id;
	@DatabaseField private String name;
	@DatabaseField private String surname;
	@DatabaseField private String address;
	@DatabaseField private String phoneNumber;
	@DatabaseField private String email;
	@DatabaseField private String username;
	@DatabaseField private String password;
	@DatabaseField private Role role;

	public User()
	{
	}

	/**
	 * Create a new user. (This method will usually only be called by object factories)
	 *
	 * @param id          The user identifier.
	 * @param name        The first name of the user.
	 * @param surname     The last name of the user.
	 * @param address     The adress ofthe user.
	 * @param phoneNumber The phone number of this user.
	 * @param email       The email address of this user.
	 * @param username    The username this user uses to log in.
	 * @param password    The password this user uses to log in.
	 * @param role        The role of this user in the system.
	 */
	public User(int id, String name, String surname, String address, String phoneNumber, String email, String username, String password, Role role)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * Get the identifier of this user.
	 *
	 * @return this.This users' identifier.
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * Give this user a new identifier.
	 *
	 * @param id The new identifier for this user.
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Get this users' first name.
	 *
	 * @return this.The users' first name.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Give this user a new first name.
	 *
	 * @param name The new first name for this user.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the last name of this user.
	 *
	 * @return this.The users' last name.
	 */
	public String getSurname()
	{
		return this.surname;
	}

	/**
	 * Give this user a new last name.
	 *
	 * @param surname The users' new last name.
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * Get this users' address.
	 *
	 * @return this.The users' address.
	 */
	public String getAddress()
	{
		return this.address;
	}

	/**
	 * Give this user a new address.
	 *
	 * @param address The users' new address.
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Get this users' phone number.
	 *
	 * @return this.The users' phone number.
	 */
	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}

	/**
	 * Give this usera new phone number.
	 *
	 * @param phoneNumber The users' new phone number.
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get this users' email address.
	 *
	 * @return This users' email address.
	 */
	public String getEmail()
	{
		return this.email;
	}

	/**
	 * Give this user a new email address.
	 *
	 * @param email This users' new email address.
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Get this users' email address.
	 *
	 * @return This users' email address.
	 */
	public String getUsername()
	{
		return this.username;
	}

	/**
	 * Give this user a new username.
	 *
	 * @param username This users' new username.
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * Get this users' password.
	 *
	 * @return This users' password.
	 */
	public String getPassword()
	{
		return this.password;
	}

	/**
	 * Give this user a new password.
	 *
	 * @param password The users' new password.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Get this users' role.
	 *
	 * @return This users' role.
	 * @see Role
	 */
	public Role getRole()
	{
		return this.role;
	}

	/**
	 * Give this user a new role
	 *
	 * @param role The users' new role.
	 * @see Role
	 */
	public void setRole(Role role)
	{
		this.role = role;
	}
}
