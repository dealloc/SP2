package be.ehb.spg3.exceptions;

// Created by Wannes Gennar. All rights reserved

/**
 * Thrown when there's an error with the query being executed.
 */
public class QueryException extends Exception
{
	/**
	 * Create a new QueryException instance.
	 *
	 * @param s A little more information on the exception thrown.
	 */
	public QueryException(String s)
	{
		super(s);
	}
}
