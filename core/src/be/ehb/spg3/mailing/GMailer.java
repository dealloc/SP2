package be.ehb.spg3.mailing;

import be.ehb.spg3.contracts.mailing.Mailer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

// Created by Wannes Gennar. All rights reserved

public class GMailer implements Mailer
{
	Message message;

	public GMailer()
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Authenticator authenticator = new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("email addres", "password");
			}
		};
		Session session = Session.getInstance(properties, authenticator);
		this.message = new MimeMessage(session);
	}

	@Override
	public Mailer from(String from)
	{
		try
		{
			this.message.setFrom(new InternetAddress(from));
		}
		catch (MessagingException e)
		{
			e.printStackTrace(); // TODO handle exception
		}

		return this;
	}

	@Override
	public Mailer to(String to)
	{
		try
		{
			this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		}
		catch (MessagingException e)
		{
			e.printStackTrace(); // TODO handle exception
		}

		return this;
	}

	@Override
	public Mailer subject(String subject)
	{
		try
		{
			this.message.setSubject(subject);
		}
		catch (MessagingException e)
		{
			e.printStackTrace(); // TODO handle exception
		}

		return this;
	}

	@Override
	public Mailer text(String msg)
	{
		try
		{
			this.message.setText(msg);
		}
		catch (MessagingException e)
		{
			e.printStackTrace(); // TODO handle exception
		}

		return this;
	}

	@Override
	public void send() throws MessagingException
	{
		Transport.send(this.message);
	}
}
