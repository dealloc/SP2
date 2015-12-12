package be.ehb.spg3.controllers;

import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

public class DatabaseViewController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<User> data = FXCollections.observableArrayList();

	@FXML
	private TableView tvTable;
	@FXML
	private TableColumn tcUsers;
	@FXML
	private TextField txtFName;
	@FXML
	private TextField txtLName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtTel;
	@FXML
	private TextField txtUsername;

	public DatabaseViewController()
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

		tcUsers.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		tvTable.setItems(data);

		tvTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
				User selectedPerson = (User) newValue;
				index.set(data.indexOf(newValue));
			}
		});
	}

	public void select(){
		txtFName.setText("SELECT");
	}

	public void save(){
		txtFName.setText("");
		txtLName.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtTel.setText("");
	}

	public void deleteSelected(){
		data.remove(index.get());
		tvTable.getSelectionModel().clearSelection();
	}

	public void resetPass(){

	}

	public void addUser(){
		/*data.add(new User(tvTable.getItems().size() + 1,
				null,
				null,
				null,
				null,
				null
		));*/
	}
}
