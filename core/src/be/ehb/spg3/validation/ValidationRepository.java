package be.ehb.spg3.validation;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.contracts.validation.EmailValidator;
import be.ehb.spg3.contracts.validation.StringValidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationRepository implements EmailValidator, StringValidator
{
	private String reason = null;

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

	@Override
	public boolean same(String... strs)
	{
		if (strs.length == 0)
			return true;

		String previous = strs[0];
		for (String str : strs)
			if (!str.equals(previous)) return false;

		return true;
	}
}
