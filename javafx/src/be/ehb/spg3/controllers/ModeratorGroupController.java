package be.ehb.spg3.controllers;

import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.groups.GroupRepository;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.ListSelectionView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved
public class ModeratorGroupController implements Initializable
{
	public TableView tblGroups;
	public TableColumn tcGroups;
	public ListSelectionView lsvPermissions;

	private List<User> users;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			List<Group> groups = resolve(GroupRepository.class).getAll();
			this.users = resolve(UserRepository.class).getAll();

			this.tblGroups.getItems().addAll(groups);
			this.tcGroups.setCellValueFactory(new PropertyValueFactory<Role, String>("name"));

			this.tblGroups.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
			{
				this.lsvPermissions.getSourceItems().clear();
				this.lsvPermissions.getTargetItems().clear();

				Group group = (Group) newValue;
				this.users.parallelStream().forEach(user ->
				{
					Platform.runLater(() ->
					{
						if (user.getGroup() != null && user.getGroup().getName().equals(group.getName()))
						{
							this.lsvPermissions.getTargetItems().add(user.getName());
						}
						else
						{
							this.lsvPermissions.getSourceItems().add(user.getName());
						}
					});
				});
			});
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void save()
	{

	}
	
	public void delete()
	{

	}
}
