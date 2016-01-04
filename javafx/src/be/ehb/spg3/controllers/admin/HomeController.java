package be.ehb.spg3.controllers.admin;

import be.ehb.spg3.auth.AuthRepository;
import be.ehb.spg3.controllers.AdminpanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class HomeController implements Initializable
{
	@FXML
	private Label lblName;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		lblName.setText(resolve(be.ehb.spg3.contracts.auth.Authenticator.class).auth().getName() + " " + resolve(be.ehb.spg3.contracts.auth.Authenticator.class).auth().getSurname());
	}

	public void manageUsers(){
		new AdminpanelController().manageUsers();
	}

	public void manageGroups(){
		new AdminpanelController().manageGroups();
	}
}
