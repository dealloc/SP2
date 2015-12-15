/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
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
import java.util.Random;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */
//TODO The use of object User -> Group (waiting for entity)
public class ManageGroupsController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<User> data = FXCollections.observableArrayList();

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

	public ManageGroupsController()
	{
	}

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		try{
			data.addAll(resolve(UserRepository.class).getAll());
		} catch (be.ehb.spg3.exceptions.ConnectivityException e){
			e.printStackTrace();
		} catch (be.ehb.spg3.exceptions.QueryException e) {
			e.printStackTrace();
		}

		tcGroups.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		tvGroups.setItems(data);

		tvGroups.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
				User selectedPerson = (User) newValue;
				index.set(data.indexOf(newValue));
				btnSave.setDisable(false);
				btnDelete.setDisable(false);
				txtGroupName.setText(data.get(index.get()).getName());
			}
		});
	}

	public void tableSort(){
		btnSave.setDisable(true);
		btnDelete.setDisable(true);
	}

	public void save(){
		data.get(index.get()).setName(txtGroupName.getText());

		User temp = data.get(index.get());
		try{
			resolve(UserRepository.class).save(temp);
		} catch (be.ehb.spg3.exceptions.ConnectivityException e){
			e.printStackTrace();
		} catch (be.ehb.spg3.exceptions.QueryException e) {
			e.printStackTrace();
		}
	}

	public void deleteSelected(){
		//TODO Remove row from database (waiting for model update)
		data.remove(index.get());
		tvGroups.getSelectionModel().clearSelection();
	}

	public void addGroup(){
		if (txtCreateGroup.getText().length() < 3){
			lblError.setText("Username must be at least 4 characters long!");
			return;
		}

		for (User u: data){
			if (txtCreateGroup.getText().equals(u.getUsername())){
				lblError.setText("Username already in use!");
				return;
			}
		}
		User temp = new User();
		temp.setUsername(txtCreateGroup.getText());
		try{
			resolve(UserRepository.class).save(temp);
		} catch (be.ehb.spg3.exceptions.ConnectivityException e){
			e.printStackTrace();
		} catch (be.ehb.spg3.exceptions.QueryException e) {
			e.printStackTrace();
		}
		data.add(temp);
	}
}
