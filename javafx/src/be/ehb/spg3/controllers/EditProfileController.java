/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.entities.users.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class EditProfileController implements Initializable
{
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtLName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtTel;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtRetypePassword;
	@FXML
	private TextField txtCheck;
	@FXML
	private Label lblError;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		txtName.setText(resolve(Authenticator.class).auth().getName());
		txtLName.setText(resolve(Authenticator.class).auth().getSurname());
		txtEmail.setText(resolve(Authenticator.class).auth().getEmail());
		txtAddress.setText(resolve(Authenticator.class).auth().getAddress());
		txtTel.setText(resolve(Authenticator.class).auth().getPhoneNumber());
	}

	public void save(){
		lblError.setText("");
		String enc =resolve(Encryptor.class).encrypt(txtCheck.getText());
		if (!enc.equals(resolve(Authenticator.class).auth().getPassword())){
			lblError.setText("Incorrect password!");
			return;
		}

		resolve(Authenticator.class).auth().setName(txtName.getText());
		resolve(Authenticator.class).auth().setSurname(txtLName.getText());
		resolve(Authenticator.class).auth().setEmail(txtEmail.getText());
		resolve(Authenticator.class).auth().setAddress(txtAddress.getText());
		resolve(Authenticator.class).auth().setPhoneNumber(txtTel.getText());

		if (txtPassword.getText().isEmpty())
			return;

		if (!txtPassword.getText().equals(txtRetypePassword.getText())){
			lblError.setText("New password does not match!");
		} else {
			resolve(Authenticator.class).auth().setPassword(txtPassword.getText());
		}

	}
}
