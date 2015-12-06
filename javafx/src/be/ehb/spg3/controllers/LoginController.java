package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jérémy Thiebaut on 12/3/15.
 */
public class LoginController
{
	@FXML
	TextField lblUsername;

	@FXML
	TextField lblPassword;

	public void login()
	{
		if (!resolve(Authenticator.class).login(lblUsername.getText(), lblPassword.getText()))
		{
			if(lblUsername.getText().isEmpty() && lblPassword.getText().isEmpty())
			{
				Notifications.create().darkStyle().text("OUPS ! Username & password are empty...").showError();
			}else
			{
				if(lblUsername.getText().isEmpty())
				{
					Notifications.create().darkStyle().text("OUPS ! Username is empty...").showError();
				}
				if(lblPassword.getText().isEmpty())
				{
					Notifications.create().darkStyle().text("OUPS ! Password is empty...").showError();
				}
			}
			if(!lblUsername.getText().isEmpty() && !lblPassword.getText().isEmpty())
				Notifications.create().darkStyle().text("OUPS ! Wrong username or password...").showError();
		} else
		{
			Notifications.create().darkStyle().text("Welcome ! You are now logged in.").showConfirm();
		}
	}

	public void register()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/register/register.fxml", false));
	}

	public void close()
	{
		System.exit(0);
	}

}
