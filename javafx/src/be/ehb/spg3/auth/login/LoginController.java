package be.ehb.spg3.auth.login;

import org.controlsfx.control.Notifications;

// Created by Wannes Gennar. All rights reserved
public class LoginController
{
	public void close()
	{
		System.exit(0);
	}

	public void login()
	{
		Notifications.create()
				.text("An error occured while logging you in")
				.showError();
	}
}