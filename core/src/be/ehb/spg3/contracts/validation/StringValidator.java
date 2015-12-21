package be.ehb.spg3.contracts.validation;

// Created by Wannes Gennar. All rights reserved

/**
 * Various functions to validate strings for a set of situations.
 */
public interface StringValidator extends Validator
{
	/**
	 * Check if one of the given strings are empty.
	 *
	 * @param strs The strings to check if it's empty or not.
	 * @return true if the string is empty, false if it's not empty.
	 */
	boolean validateEmpty(String... strs);

	/**
	 * Check if all given strings are equal.
	 * @param strs The strings to compare.
	 * @return True if all strings are equal, false if one isn't.
	 */
	boolean same(String... strs);
}
