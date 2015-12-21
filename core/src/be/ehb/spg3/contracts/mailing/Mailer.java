package be.ehb.spg3.contracts.mailing;

import javax.mail.MessagingException;

// Created by Wannes Gennar. All rights reserved

/**
 * Provides a contract for integrating mailing services.
 */
public interface Mailer
{
	/**
	 * Set the sender of the email. (not needed for some implementations)
	 *
	 * @param from The sender for this email.
	 * @return The Mailer itself for chaining.
	 */
	Mailer from(String from);

	/**
	 * Set the receiver of the email.
	 *
	 * @param to The receiver for this email.
	 * @return The Mailer itself for chaining.
	 */
	Mailer to(String to);

	/**
	 * Set the subject of the email.
	 *
	 * @param subject The subject for this email.
	 * @return The Mailer itself for chaining.
	 */
	Mailer subject(String subject);

	/**
	 * Set the content of the email or the template to parse depending on the implementing service.
	 *
	 * @param msg The content or template.
	 * @return The Mailer itself for chaining.
	 */
	Mailer text(String msg);

	/**
	 * Send the email.
	 *
	 * @throws MessagingException If something goes wrong with sending the email.
	 */
	void send() throws MessagingException;
}
