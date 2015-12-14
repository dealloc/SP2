package be.ehb.spg3.validation;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.contracts.validation.EmailValidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationRepository implements EmailValidator
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
}
