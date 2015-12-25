/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.groups.GroupRepository;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.permissions.PermissionRepository;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.roles.RoleRepository;
import com.sun.deploy.xml.XMLable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class ManageRolesController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<Role> roles = FXCollections.observableArrayList();
	private IntegerProperty indexPermissions = new SimpleIntegerProperty();
	private ObservableList<Permission> permissions = FXCollections.observableArrayList();
	private ObservableList<Permission> allPermissions = FXCollections.observableArrayList();

	@FXML
	private TableView tvRoles;
	@FXML
	private TableColumn tcRoles;
	@FXML
	private TextField txtRoleName;
	@FXML
	private TableView tvPermissions;
	@FXML
	private ComboBox cbPermissions;
	@FXML
	private TextField txtCreateRoleName;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnDelete;
	@FXML
	private Label lblError;
	@FXML
	private Label lblConfirm;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		try
		{
			roles.addAll(resolve(RoleRepository.class).getAll());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcRoles.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
		tvRoles.setItems(roles);

		try
		{
			allPermissions.addAll(resolve(PermissionRepository.class).getAll());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//TODO fill combobox
		cbPermissions.setCellFactory(new PropertyValueFactory<Permission, String>("name"));
		cbPermissions.setItems(permissions);

		tcRoles.setCellValueFactory(new PropertyValueFactory<Permission, String>("name"));

		tvRoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				index.set(roles.indexOf(newValue));
				btnSave.setDisable(false);
				btnDelete.setDisable(false);
				txtRoleName.setText(roles.get(index.get()).getName());
				permissions.addAll(roles.get(index.get()).getPermissions());
				tvPermissions.setItems(permissions);
			}
		});
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
		resetLbl();
		if (txtCreateRoleName.getText().length() < 3)
		{
			lblError.setText("Rolename must be at least 3 characters long!");
			return;
		}

		for (Role g : roles)
		{
			if (txtCreateRoleName.getText().equals(g.getName()))
			{
				lblError.setText("Groupname already in use!");
				return;
			}
		}
		Role temp = new Role();
		temp.setName(txtCreateRoleName.getText());
		try
		{
			resolve(RoleRepository.class).save(temp);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		roles.add(temp);
		lblConfirm.setText("Role added.");
	}

	public void sortRoles(){
		btnSave.setDisable(true);
		btnDelete.setDisable(true);
	}

	public void sortPermissions(){

	}

	public void resetLbl(){
		lblConfirm.setText("");
		lblError.setText("");
	}
}
