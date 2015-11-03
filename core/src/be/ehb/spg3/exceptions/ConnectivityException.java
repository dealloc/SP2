package be.ehb.spg3.exceptions;

// Created by Wannes Gennar. All rights reserved

/**
 * Thrown when there's a problem with the connection between the database and the application.
 */
public class ConnectivityException extends Exception
{
	/**
	 * Create a new ConnectivityException instance.
	 *
	 * @param s A little more information on the exception thrown
	 */
	public ConnectivityException(String s)
	{
		super(s);
	}
}
