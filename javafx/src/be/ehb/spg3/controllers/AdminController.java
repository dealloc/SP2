package be.ehb.spg3.controllers;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminController implements Initializable
{

	@FXML
	private Label lblUserName;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		this.lblUserName.setText("Jeroen");
	}

	public void close()
	{
		Platform.exit();
	}
}
