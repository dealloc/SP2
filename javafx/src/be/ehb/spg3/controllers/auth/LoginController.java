package be.ehb.spg3.controllers.auth;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.users.User;
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
			Notifications.create().darkStyle().text("OOPS! Wrong username or password...").showError();
		} else
		{
			Notifications.create().darkStyle().text("Welcome! You are now logged in.").showConfirm();

			Role role = resolve(Authenticator.class).auth().getRole();
			if (role != null && role.getName().equals("admin"))
				resolve(EventBus.class).fire(new SwitchScreenEvent("design/adminpanel.fxml", true));
			else if (role != null && role.getName().equals("moderator"))
			{
				resolve(EventBus.class).fire(new SwitchScreenEvent("design/moderatorpanel.fxml", true));
			}
			else
				resolve(EventBus.class).fire(new SwitchScreenEvent("design/userpanel.fxml", true));
		}

	}

	public void register()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/register/register.fxml", false));
	}

	public void loginAsGuest()
	{
		resolve(Authenticator.class).sudo(User.GUEST);
		Notifications.create().darkStyle().text("Welcome! You are now logged in.").showConfirm();
		resolve(EventBus.class).fire(new SwitchScreenEvent("design/userpanel.fxml", true));
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
