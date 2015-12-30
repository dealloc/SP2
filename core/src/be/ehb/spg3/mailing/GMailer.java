package be.ehb.spg3.mailing;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.mailing.Mailer;
import be.ehb.spg3.events.errors.ErrorEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved

/**
 * Implementation of the Mailer contract using the Gmail services.
 */
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

	/**
	 * Set the sender of the email. (not needed for some implementations)
	 *
	 * @param from The sender for this email.
	 * @return The Mailer itself for chaining.
	 */
	@Override
	public Mailer from(String from)
	{
		try
		{
			this.message.setFrom(new InternetAddress(from));
		}
		catch (MessagingException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}

		return this;
	}

	/**
	 * Set the receiver of the email.
	 *
	 * @param to The receiver for this email.
	 * @return The Mailer itself for chaining.
	 */
	@Override
	public Mailer to(String to)
	{
		try
		{
			this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		}
		catch (MessagingException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}

		return this;
	}

	/**
	 * Set the subject of the email.
	 *
	 * @param subject The subject for this email.
	 * @return The Mailer itself for chaining.
	 */
	@Override
	public Mailer subject(String subject)
	{
		try
		{
			this.message.setSubject(subject);
		}
		catch (MessagingException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}

		return this;
	}

	/**
	 * Set the content of the email or the template to parse depending on the implementing service.
	 *
	 * @param msg The content or template.
	 * @return The Mailer itself for chaining.
	 */
	@Override
	public Mailer text(String msg)
	{
		try
		{
			this.message.setText(msg);
		}
		catch (MessagingException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}

		return this;
	}

	/**
	 * Send the email.
	 *
	 * @throws MessagingException If something goes wrong with sending the email.
	 */
	@Override
	public void send() throws MessagingException
	{
		Transport.send(this.message);
	}
}
