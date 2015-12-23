package be.ehb.spg3.validation;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.contracts.validation.EmailValidator;
import be.ehb.spg3.contracts.validation.StringValidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Implements validators for various purposes.
 */
public class ValidationRepository implements EmailValidator, StringValidator
{
	private String reason = null;

	/**
	 * Validates given email address.
	 *
	 * @param email The address to validate.
	 * @return True if the address is valid, False if it isn't.
	 */
	@Override
	public boolean validateEmail(String email)
	{
		try
		{
			new InternetAddress(email).validate();
			return true;
		}
		catch (AddressException ex)
		{
			this.reason = ex.getMessage();
			return false;
		}
	}

	/**
	 * Get the reason why a validation failed.
	 * @return A string explaining why the validation failed, or null if the validation passed.
	 */
	@Override
	public String getReason()
	{
		return this.reason;
	}

	@Override
	public boolean validateEmpty(String... str)
	{
		for (String string : str)
		{
			if (string.isEmpty()) return false;
		}

		return true;
	}

	/**
	 * Check if one of the given strings are empty.
	 *
	 * @param strs The strings to check if it's empty or not.
	 * @return true if the string is empty, false if it's not empty.
	 */
	@Override
	public boolean same(String... strs)
	{
		if (strs.length == 0)
			return true;

		String previous = strs[0];
		for (String str : strs)
		{
			if (!str.equals(previous))
			{
				this.reason = "'" + previous + "' is not equal to '" + str + "'";
				return false;
			}
		}

		return true;
	}
}
