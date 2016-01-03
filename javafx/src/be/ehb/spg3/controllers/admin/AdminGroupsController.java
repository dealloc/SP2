/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers.admin;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.groups.GroupRepository;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.application.Platform;
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
import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */
//TODO The use of object User -> Group (waiting for entity)
public class AdminGroupsController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<Group> data = FXCollections.observableArrayList();
	private List<User> users;

	@FXML
	private TableView tvGroups;
	@FXML
	private TableColumn tcGroups;
	@FXML
	private TextField txtGroupName;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnDelete;
	@FXML
	private TextField txtCreateGroup;
	@FXML
	private ListSelectionView lsvUsers;

	public AdminGroupsController()
	{
	}

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		try
		{
			data.addAll(resolve(GroupRepository.class).getAll());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcGroups.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
		tvGroups.setItems(data);

		tvGroups.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				index.set(data.indexOf(newValue));
				btnSave.setDisable(false);
				btnDelete.setDisable(false);
				txtGroupName.setText(data.get(index.get()).getName());
				loadUsers(data.get(index.get()));
			}
		});
	}

	public void tableSort()
	{
		btnSave.setDisable(true);
		btnDelete.setDisable(true);
	}

	public void save()
	{
		if (txtGroupName.getText().length() < 3){
			Notifications.create().text("Group name must be at least 3 characters long!").darkStyle().showError();
			return;
		}
		if (txtGroupName.getText().length() > 30){
			Notifications.create().text("Group name cannot be longer than 30 characters!").darkStyle().showError();
			return;
		}

		data.get(index.get()).setName(txtGroupName.getText());

		Group group = data.get(index.get());
		this.users.stream().forEach(user -> {
			if (this.lsvUsers.getTargetItems().contains(user.getUsername()))
				user.setGroup(group);
			else if (this.lsvUsers.getSourceItems().contains(user.getUsername()))
				user.setGroup(null);

			try
			{
				resolve(UserRepository.class).update(user);
				resolve(GroupRepository.class).save(data.get(index.get()));
			}
			catch (SQLException e)
			{
				resolve(EventBus.class).fire(new ErrorEvent(e));
			}
		});
		Notifications.create().text("Group saved!").darkStyle().showConfirm();
	}

	public void deleteSelected()
	{
		try
		{
			resolve(GroupRepository.class).delete(data.get(index.get()));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		data.remove(index.get());
		tvGroups.getSelectionModel().clearSelection();
		Notifications.create().text("Group removed").darkStyle().showConfirm();
	}

	public void addGroup()
	{
		if (txtCreateGroup.getText().length() < 3)
		{
			Notifications.create().text("Group name must be at least 3 characters long!").darkStyle().showError();
			return;
		}
		if (txtCreateGroup.getText().length() > 30){
			Notifications.create().text("Group name cannot be longer than 30 characters!").darkStyle().showError();
			return;
		}

		for (Group g : data)
		{
			if (txtCreateGroup.getText().equals(g.getName()))
			{
				Notifications.create().text("Group name already in use!").darkStyle().showError();
				return;
			}
		}
		Group temp = new Group();
		temp.setName(txtCreateGroup.getText());
		try
		{
			resolve(GroupRepository.class).save(temp);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		data.add(temp);
		Notifications.create().text("Group created").darkStyle().showConfirm();
	}

	public void loadUsers(Group group)
	{
		this.lsvUsers.getTargetItems().clear();
		this.lsvUsers.getSourceItems().clear();

		try
		{
			if (group == null)
			{
				return;
			}

			this.users = resolve(UserRepository.class).getAll();
			this.users.parallelStream().forEach(user ->
			{
				Platform.runLater(() ->
				{
					if (user.getGroup() != null && user.getGroup().getName().equals(group.getName()))
					{
						this.lsvUsers.getTargetItems().add(user.getUsername());
					} else if (user.getGroup() == null)
					{
						this.lsvUsers.getSourceItems().add(user.getUsername());
					}
				});
			});
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
