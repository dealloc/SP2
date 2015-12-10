package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.application.Platform;
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
			Notifications.create().darkStyle().text("OOPS ! Wrong username or password...").showError();
		} else
		{
			Notifications.create().darkStyle().text("Welcome ! You are now logged in.").showConfirm();
			resolve(EventBus.class).fire(new SwitchScreenEvent("design/panel.fxml", true));
		}

	}

	public void register()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/register/register.fxml", false));
	}

	public void loginAsGuest()
	{
		if (!resolve(Authenticator.class).login("Guest", "guest"))
		{
			Notifications.create().darkStyle().text("OOPS ! Wrong username or password...").showError();
		} else
		{
			Notifications.create().darkStyle().text("Welcome ! You are now logged in.").showConfirm();
			resolve(EventBus.class).fire(new SwitchScreenEvent("design/panel.fxml", true));
		}
	}

	public void forgotPass()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/passRecovery/passRecovery.fxml", false));
	}

	public void close()
	{
		Platform.exit();
	}

}
