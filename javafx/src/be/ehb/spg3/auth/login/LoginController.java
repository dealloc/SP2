package be.ehb.spg3.auth.login;

import be.ehb.spg3.contracts.auth.Authenticator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved
public class LoginController
{
	@FXML
	TextField txtUsername;

	@FXML
	TextField txtPassword;

	public void close()
	{
		System.exit(0);
	}

	public void login()
	{
		Notifications notification = Notifications.create();
		if (resolve(Authenticator.class).login(txtUsername.getText(), txtPassword.getText()))
			notification.text("logged in!");
		else
			notification.text("not logged in!");

		notification.show();
	}
}