package be.ehb.spg3.controllers;

// Created by Jérémy Thiebaut. All rights reserved

import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.exceptions.ConnectivityException;
import be.ehb.spg3.exceptions.QueryException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class RegisterController
{
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

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

	public void register() throws QueryException, ConnectivityException
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
		User user = null;
		if (tfName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfEmail.getText().isEmpty() || tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty() || tfPasswordRepeat.getText().isEmpty())
		{
			Notifications.create().darkStyle().text("OOPS ! All fields are required...").showError();
		} else
		{
			if ((tfPassword.getText().equals(tfPasswordRepeat.getText())))
			{
				matcher = pattern.matcher(tfEmail.getText());

				if (matcher.matches() == false)
				{
					Notifications.create().darkStyle().text("OOPS ! Bad email address...").showError();
				} else
				{
					user = new User(tfName.getText(), tfLastName.getText(), tfEmail.getText(), tfUsername.getText(), resolve(Encryptor.class).encrypt(tfPassword.getText()));
					try
					{
						resolve(UserRepository.class).save(user);
					}
					catch (QueryException e)
					{
						e.printStackTrace();
					}
					catch (ConnectivityException e)
					{
						e.printStackTrace();
					}

					Notifications.create().darkStyle().text("You have successfully registered!").showConfirm();
//			        resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/login/login.fxml", false));
				}
			} else
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
