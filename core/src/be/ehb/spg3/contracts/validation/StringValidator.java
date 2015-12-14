package be.ehb.spg3.contracts.validation;

// Created by Wannes Gennar. All rights reserved

/**
 * Various functions to validate strings for a set of situations.
 */
public interface StringValidator
{
	/**
	 * Check if the given string is empty.
	 *
	 * @param str The string to check if it's empty or not.
	 * @return true if the string is empty, false if it's not empty.
	 */
	boolean validateEmpty(String... str);
}
