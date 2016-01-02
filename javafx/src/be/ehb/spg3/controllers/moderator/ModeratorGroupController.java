package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.groups.GroupRepository;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

// Created by Wannes Gennar. All rights reserved
public class ModeratorGroupController implements Initializable
{
	@FXML
	private Label lblGroupname;
	@FXML
	private ListSelectionView lsvUsers;
	@FXML
	private Button btnSave;

	private List<User> users;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			Group group = resolve(Authenticator.class).auth().getGroup();
			if (group == null){
				lblGroupname.setText("You are not in a group! (Contact an admin)");
				btnSave.setDisable(true);
				return;
			}

			this.users = resolve(UserRepository.class).getAll();
			lblGroupname.setText(group.getName());
			this.users.parallelStream().forEach(user ->
			{
				Platform.runLater(() ->
				{
					if (user.getGroup() != null && user.getGroup().getName().equals(group.getName()))
					{
						this.lsvUsers.getTargetItems().add(user.getUsername());
					}
					else if (user.getGroup() == null)
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

	public void save()
	{
		Group group = resolve(Authenticator.class).auth().getGroup();
		this.users.stream().forEach(user -> {
			if (this.lsvUsers.getTargetItems().contains(user.getUsername()))
				user.setGroup(group);
			else if (this.lsvUsers.getSourceItems().contains(user.getUsername()))
				user.setGroup(null);
			
			try
			{
				resolve(UserRepository.class).update(user);
			}
			catch (SQLException e)
			{
				resolve(EventBus.class).fire(new ErrorEvent(e));
			}
		});
		Notifications.create().text("Group saved!").darkStyle().showConfirm();
	}
	
	public void delete()
	{

	}
}
