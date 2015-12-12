package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.encryption.Encryptor;
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
import java.util.Random;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

public class UserTableController implements Initializable
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
	private Button btnSave;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnReset;
	@FXML
	private TextField txtUsername;
	@FXML
	private Label lblError;

	public UserTableController()
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
				btnSave.setDisable(false);
				btnDelete.setDisable(false);
				btnReset.setDisable(false);
				txtFName.setText(data.get(index.get()).getName());
				txtLName.setText(data.get(index.get()).getSurname());
				txtEmail.setText(data.get(index.get()).getEmail());
				txtAddress.setText(data.get(index.get()).getAddress());
				txtTel.setText(data.get(index.get()).getPhoneNumber());
			}
		});
	}

	public void tableSort(){
		btnSave.setDisable(true);
		btnDelete.setDisable(true);
		btnReset.setDisable(true);
	}

	public void save(){
		data.get(index.get()).setName(txtFName.getText());
		data.get(index.get()).setSurname(txtLName.getText());
		data.get(index.get()).setEmail(txtEmail.getText());
		data.get(index.get()).setAddress(txtAddress.getText());
		data.get(index.get()).setPhoneNumber(txtTel.getText());

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
		tvTable.getSelectionModel().clearSelection();
	}

	public void resetPass(){
		User temp = data.get(index.get());
		temp.setPassword(resolve(Encryptor.class).encrypt(randomString()));
		try{
			resolve(UserRepository.class).save(temp);
		} catch (be.ehb.spg3.exceptions.ConnectivityException e){
			e.printStackTrace();
		} catch (be.ehb.spg3.exceptions.QueryException e) {
			e.printStackTrace();
		}
	}

	public String randomString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		//Tijdelijk syso voor testing!
		System.out.println("New pass: " + sb.toString());
		return sb.toString();
	}

	public void addUser(){
		if (txtUsername.getText().length() < 4){
			lblError.setText("Username must be at least 4 characters long!");
			return;
		}

		for (User u: data){
			if (txtUsername.getText().equals(u.getUsername())){
				lblError.setText("Username already in use!");
				return;
			}
		}
		User temp = new User();
		temp.setUsername(txtUsername.getText());
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
