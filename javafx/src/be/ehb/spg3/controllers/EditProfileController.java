/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.encryption.Hasher;
import be.ehb.spg3.controllers.admin.DatabaseViewController;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
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
	private PasswordField txtPassword;
	@FXML
	private PasswordField txtRetypePassword;
	@FXML
	private PasswordField txtCheck;
	@FXML
	private Label lblError;
	@FXML
	private Label lblConfirm;

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
		lblConfirm.setText("");
		lblError.setText("");
		String enc = resolve(Hasher.class).hash(txtCheck.getText());
		if (!enc.equals(resolve(Authenticator.class).auth().getPassword())){
			lblError.setText("Incorrect password!");
			return;
		}

		resolve(Authenticator.class).auth().setName(txtName.getText());
		resolve(Authenticator.class).auth().setSurname(txtLName.getText());
		resolve(Authenticator.class).auth().setEmail(txtEmail.getText());
		resolve(Authenticator.class).auth().setAddress(txtAddress.getText());
		resolve(Authenticator.class).auth().setPhoneNumber(txtTel.getText());

		if ((!txtPassword.getText().equals(txtRetypePassword.getText()))){
			lblError.setText("New password does not match!");
		} else if (!txtPassword.getText().isEmpty()) {
			resolve(Authenticator.class).auth().setPassword(resolve(Hasher.class).hash(txtPassword.getText()));
		}

		lblConfirm.setText("Saved changes\n");

		try
		{
			resolve(UserRepository.class).save(resolve(Authenticator.class).auth());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		txtCheck.setText("");
	}
}
