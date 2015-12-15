/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageRolesController implements Initializable
{
	@FXML
	private TableView tvRoles;
	@FXML
	private TextField txtRoleName;
	@FXML
	private TableView tvPermissions;
	@FXML
	private ComboBox cbPermissions;
	@FXML
	private TextField txtCreateRoleName;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{

	}

	public void removePermission(){

	}

	public void addPermission(){

	}

	public void saveRole(){

	}

	public void deleteSelectedRow(){

	}

	public void addRole(){

	}

	public void sortRoles(){

	}

	public void sortPermissions(){

	}
}
