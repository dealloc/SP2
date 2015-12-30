/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.permissions.PermissionRepository;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.roles.RoleRepository;
import be.ehb.spg3.entities.users.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class ManageRolesController implements Initializable
{
	@FXML
	public ListSelectionView lsvPermissions;
	@FXML
	public TableView tblRoles;
	@FXML
	public TableColumn tcRoles;

	private List<Permission> permissions;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		try
		{
			List<Role> roles = resolve(RoleRepository.class).getAll();
			this.permissions = resolve(PermissionRepository.class).getAll();


			this.tblRoles.getItems().addAll(roles);
			this.tcRoles.setCellValueFactory(new PropertyValueFactory<Role, String>("name"));

			this.tblRoles.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
			{
				this.lsvPermissions.getSourceItems().clear();
				this.lsvPermissions.getTargetItems().clear();

				Role role = (Role) newValue;
				this.permissions.parallelStream().forEach(permission ->
				{
					Platform.runLater(() ->
					{
						if (role.getPermissions().contains(permission))
							this.lsvPermissions.getTargetItems().add(permission.getName());
						else
							this.lsvPermissions.getSourceItems().add(permission.getName());
					});
				});
			});
		}
		catch (SQLException e)
		{
			Notifications.create()
					.text("There was an error retrieving the roles")
					.darkStyle()
					.showError();
		}
	}

	public void save()
	{
		try
		{
			Role role = (Role) this.tblRoles.getSelectionModel().getSelectedItem();
			role.getPermissions().clear();
			this.permissions.parallelStream().filter(permission -> this.lsvPermissions.getTargetItems().contains(permission.getName()))
					.forEach(p -> { role.getPermissions().add(p); });
			resolve(RoleRepository.class).save(role);
			Notifications.create()
					.text("Permissions saved!")
					.darkStyle()
					.showConfirm();
		}
		catch (SQLException ex)
		{
			Notifications.create()
					.text("There was an error saving the permissions!")
					.darkStyle()
					.showConfirm();
		}
	}

	public void delete()
	{
		try
		{
			Role role = (Role) this.tblRoles.getSelectionModel().getSelectedItem();
			resolve(RoleRepository.class).delete(role);
			this.tblRoles.getItems().remove(this.tblRoles.getSelectionModel().getSelectedItem());
			Notifications.create()
					.text("Role deleted!")
					.darkStyle()
					.showConfirm();
		}
		catch (SQLException ex)
		{
			Notifications.create()
					.text("There was an error deleting this role!")
					.darkStyle()
					.showConfirm();
		}
	}
}
