/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.permissions.PermissionRepository;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.roles.RoleRepository;
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
	private IntegerProperty indexAddPermission = new SimpleIntegerProperty();
	private ObservableList<Permission> addPermission = FXCollections.observableArrayList();
	private IntegerProperty indexHasPermission = new SimpleIntegerProperty();
	private ObservableList<Permission> hasPermission = FXCollections.observableArrayList();
	private ObservableList<Permission> allPermissions = FXCollections.observableArrayList();

	@FXML
	private TableView tvRoles;
	@FXML
	private TableColumn tcRoles;
	@FXML
	private TableView tvHasPermission;
	@FXML
	private TableColumn tcHasPermission;
	@FXML
	private TableView tvAddPermission;
	@FXML
	private TableColumn tcAddPermission;
	@FXML
	private TextField txtRoleName;
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
			allPermissions.addAll(resolve(PermissionRepository.class).getAll());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		//Fills "role" table with objects from database and overrides change method
		tvRoles.setItems(roles);
		tcRoles.setCellValueFactory(new PropertyValueFactory<Role, String>("name"));
		tvRoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				index.set(roles.indexOf(newValue));
				btnSave.setDisable(false);
				btnDelete.setDisable(false);
				txtRoleName.setText(roles.get(index.get()).getName());
				addPermission.clear();
				indexAddPermission.set(0);
				indexHasPermission.set(0);
				System.out.println(roles.get(index.get()).getName());
				for (Permission p : roles.get(index.get()).getPermissions())
				{
					System.out.println(p.getName());
				}//TODO Fix observablelist voor permissions
				hasPermission.setAll(roles.get(index.get()).getPermissions());
				for (Permission p : allPermissions)
				{
					boolean has = false;
					for (Permission p2 : hasPermission)
					{
						if (p.equals(p2))
						{
							has = true;
						}
					}
					if (!has)
					{
						addPermission.add(p);
					}
				}
			}
		});

		//Fills "add a permission" table with objects from database and overrides change method
		tvAddPermission.setItems(addPermission);
		tcAddPermission.setCellValueFactory(new PropertyValueFactory<Permission, String>("name"));
		tvAddPermission.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				indexAddPermission.set(addPermission.indexOf(newValue));
			}
		});

		//Fills "has permission" table with objects from database and overrides change method
		tvHasPermission.setItems(hasPermission);
		tcHasPermission.setCellValueFactory(new PropertyValueFactory<Role, String>("name"));
		tvHasPermission.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				indexHasPermission.set(hasPermission.indexOf(newValue));
			}
		});
	}

	public void removePermission()
	{
		addPermission.add(hasPermission.get(indexHasPermission.get()));
		hasPermission.remove(indexHasPermission.get());
	}

	public void addPermission()
	{
		hasPermission.add(addPermission.get(indexAddPermission.get()));
		addPermission.remove(indexAddPermission.get());
	}

	public void saveRole()
	{
		resetLbl();
		roles.get(index.get()).setName(txtRoleName.getText());
		roles.get(index.get()).setPermissions(hasPermission);

		try
		{
			resolve(RoleRepository.class).save(roles.get(index.get()));
			lblConfirm.setText("Role changes saved.");
			roles.setAll(resolve(RoleRepository.class).getAll());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteSelectedRole()
	{
		resetLbl();
		try
		{
			resolve(RoleRepository.class).delete(roles.get(index.get()));
			lblConfirm.setText("Role removed.");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void addRole()
	{
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

	public void sortRoles()
	{
		btnSave.setDisable(true);
		btnDelete.setDisable(true);
	}

	public void sortPermissions()
	{

	}

	public void resetLbl()
	{
		lblConfirm.setText("");
		lblError.setText("");
	}
}
