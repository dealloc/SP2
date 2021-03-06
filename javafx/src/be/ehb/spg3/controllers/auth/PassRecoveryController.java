package be.ehb.spg3.controllers.auth;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.mailing.Mailer;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Jérémy Thiebaut. All rights reserved
public class PassRecoveryController
{
	@FXML
	TextField tfUsername;

	public void recoverPass() throws SQLException, MessagingException
	{
		List<User> users = resolve(UserRepository.class).findByField("username", tfUsername.getText());
		if (users.isEmpty())
		{
			Notifications.create().text("Geen gebruiker gevonden").darkStyle().showError();
		}
		else
		{
			User user = users.get(0);
			resolve(Mailer.class).from("info@prready.com")
					.to("gr3.pr.ready@gmail.com")
					.subject("Vergeten wachtwoord")
					.text("User " + user.getName() + " (" + user.getEmail() + ") is zijn wachtwoord vergeten en heeft een reset aangevraagd")
					.send();

			Notifications.create()
					.text("We send you an email!")
					.darkStyle()
					.showInformation();
		}
	}

	public void close()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/login/login.fxml", false));
	}
}
