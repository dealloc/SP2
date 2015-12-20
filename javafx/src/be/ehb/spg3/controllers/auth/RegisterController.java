package be.ehb.spg3.controllers.auth;

// Created by Jérémy Thiebaut. All rights reserved

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.encryption.Hasher;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.validation.EmailValidator;
import be.ehb.spg3.contracts.validation.StringValidator;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class RegisterController
{
	@FXML
	private TextField tfName;

	@FXML
	private TextField tfLastName;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfUsername;

	@FXML
	private TextField tfPassword;

	@FXML
	private TextField tfPasswordRepeat;

	public void register() throws SQLException
	{
		StringValidator validator = resolve(StringValidator.class);
		String fname = this.tfName.getText();
		String lname = this.tfLastName.getText();
		String email = this.tfEmail.getText();
		String username = this.tfUsername.getText();
		String password = this.tfPassword.getText();
		String repeat_password = this.tfPasswordRepeat.getText();

		if (!validator.validateEmpty(fname, lname, email, username, password, repeat_password))
		{
			Notifications.create().darkStyle().text("OOPS ! All fields are required...").showError();
		}
		else
		{
			if (validator.same(password, repeat_password))
			{
				if (!resolve(EmailValidator.class).validateEmail(email))
				{
					Notifications.create().darkStyle().text("OOPS ! Bad email address...").showError();
				}
				else
				{
					User user = new User(fname, lname, email, username, resolve(Hasher.class).hash(password));
					try
					{
						resolve(UserRepository.class).save(user);
						resolve(Authenticator.class).sudo(user); // set authenticated user
					}
					catch (SQLException e)
					{
						e.printStackTrace(); // TODO handle exception
					}

					Notifications.create().darkStyle().text("You have successfully registered!").showConfirm();
					resolve(EventBus.class).fire(new SwitchScreenEvent("design/panel.fxml", true));
				}
			}
			else
			{
				Notifications.create().darkStyle().text("OOPS ! Password doesn't match confirmation..").showError();
			}
		}

	}

	public void close()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/login/login.fxml", false));
	}
}
