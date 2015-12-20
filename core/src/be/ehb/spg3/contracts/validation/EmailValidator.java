package be.ehb.spg3.contracts.validation;

// Created by Wannes Gennar. All rights reserved

/**
 * Provides functionality to validate email addresses.
 * All email addresses have to conform to RFC 5321 and/or RFC 5322.
 */
public interface EmailValidator extends Validator
{
	/**
	 * Validates given email address.
	 *
	 * @param email The address to validate.
	 * @return True if the address is valid, False if it isn't.
	 */
	boolean validateEmail(String email);
}