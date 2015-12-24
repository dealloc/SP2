/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class DashboardController implements Initializable
{
	@FXML
	private Label lblName;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		//lblName.setText(resolve(be.ehb.spg3.contracts.auth.Authenticator.class).auth().getName() + " " + resolve(be.ehb.spg3.contracts.auth.Authenticator.class).auth().getSurname());
	}
}
