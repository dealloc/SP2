/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.groups.GroupRepository;
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

/**
 * Created by Jeroen_2 on 2/12/2015.
 */
//TODO The use of object User -> Group (waiting for entity)
public class AdminGroupsController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<Group> data = FXCollections.observableArrayList();

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
	private Label lblError;
	@FXML
	private Label lblConfirm;

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
		data.get(index.get()).setName(txtGroupName.getText());
		try
		{
			resolve(GroupRepository.class).save(data.get(index.get()));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		lblConfirm.setText("Group saved.");
	}

	public void deleteSelected()
	{
		resetLbl();
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
		lblConfirm.setText("Group removed.");
	}

	public void addGroup()
	{
		resetLbl();
		if (txtCreateGroup.getText().length() < 3)
		{
			lblError.setText("Groupname must be at least 3 characters long!");
			return;
		}

		for (Group g : data)
		{
			if (txtCreateGroup.getText().equals(g.getName()))
			{
				lblError.setText("Groupname already in use!");
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
		lblConfirm.setText("Group added.");
	}

	public void resetLbl(){
		lblConfirm.setText("");
		lblError.setText("");
	}
}
