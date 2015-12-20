package be.ehb.spg3.contracts.mailing;

import javax.mail.MessagingException;

// Created by Wannes Gennar. All rights reserved
public interface Mailer
{
	Mailer from(String from);

	Mailer to(String to);

	Mailer subject(String subject);

	Mailer text(String msg);

	void send() throws MessagingException;
}
