package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

public class DatabaseViewController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private final ObservableList<User> data =
			FXCollections.observableArrayList(
					new User(1, "Jacob", "Smith", "jacob.smith@prready.com", "Overal en nergens", "04444444"),
					new User(2, "Peter", "Parker", "peter.parker@prready.com", "Queens", "0412345678"),
					new User(3, "Leonardo", "Da Vinci", "leo.dv@prready.com", "Florentijnse Republiek", "-")
			);

	@FXML
	private TableView tvTable;
	@FXML
	private FlowPane fpAdd;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtTel;


	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{

	}

	public void addRow(){
		fpAdd.setVisible(true);
	}

	public void addCommit(){
			data.add(new User(tvTable.getItems().size() + 1,
					txtName.getText(),
					txtLastName.getText(),
					txtEmail.getText(),
					txtAddress.getText(),
					txtTel.getText()
			));

		txtName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtTel.setText("");

		fpAdd.setVisible(false);
	}

	public void deleteSelected(){
		data.remove(index.get());
		tvTable.getSelectionModel().clearSelection();
	}

	public void setUsers() {
		/* Hardcoded test! */
		TableColumn idCol = new TableColumn("ID");
		TableColumn firstNameCol = new TableColumn("First Name");
		TableColumn lastNameCol = new TableColumn("Last Name");
		TableColumn emailCol = new TableColumn("Email");
		TableColumn adresCol = new TableColumn("Address");
		TableColumn telCol = new TableColumn("Tel.");
		idCol.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		firstNameCol.setOnEditCommit( //Als ge de changes wilt committen.
				new EventHandler<TableColumn.CellEditEvent<User, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<User, String> t) {
						((User) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
						).setFirstName(t.getNewValue());
					}
				});
		lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		adresCol.setCellValueFactory(new PropertyValueFactory<User, String>("adres"));
		adresCol.setCellFactory(TextFieldTableCell.forTableColumn());
		telCol.setCellValueFactory(new PropertyValueFactory<User, String>("tel"));
		telCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tvTable.setItems(data);
		tvTable.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol, adresCol, telCol);

		tvTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
				User selectedPerson = (User) newValue;
				index.set(data.indexOf(newValue));
			}
		});
	}



	/* Hardcoded test! */
	public static class User {
		private final SimpleIntegerProperty id;
		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty email;
		private final SimpleStringProperty adres;
		private final SimpleStringProperty tel;

		private User(int id, String fName, String lName, String email, String adres, String tel) {
			this.id = new SimpleIntegerProperty(id);
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.email = new SimpleStringProperty(email);
			this.adres = new SimpleStringProperty(adres);
			this.tel = new SimpleStringProperty(tel);
		}

		public int getId(){
			return id.get();
		}

		public void setId(int id){
			this.id.set(id);
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

		public String getLastName() {
			return lastName.get();
		}

		public void setLastName(String fName) {
			lastName.set(fName);
		}

		public String getEmail() {
			return email.get();
		}

		public void setEmail(String fName) {
			email.set(fName);
		}

		public String getAdres() {
			return adres.get();
		}

		public void setAdres(String a) {
			lastName.set(a);
		}

		public String getTel() {
			return tel.get();
		}

		public void setTel(String t) {
			email.set(t);
		}

	}
}
