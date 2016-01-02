package be.ehb.spg3.controllers.admin;

import be.ehb.spg3.contracts.encryption.Hasher;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.mailing.Mailer;
import be.ehb.spg3.contracts.validation.EmailValidator;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.roles.RoleRepository;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.errors.ErrorEvent;
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
import org.controlsfx.control.Notifications;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

public class ManageUsersController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<User> data = FXCollections.observableArrayList();
	List<Role> roles;

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
	private ComboBox cbRole;

	public ManageUsersController()
	{
	}

	//TODO update na aanpassingen in database.
	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		try
		{
			data.addAll(resolve(UserRepository.class).getAll());
			this.roles = resolve(RoleRepository.class).getAll();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		cbRole.getItems().addAll("admin", "moderator", "user");

		tcUsers.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		tvTable.setItems(data);

		tvTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
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
				if (data.get(index.get()).getRole() == null)
					cbRole.setValue("user");
				else
					cbRole.setValue(data.get(index.get()).getRole().getName());
			}
		});
	}

	public void tableSort()
	{
		btnSave.setDisable(true);
		btnDelete.setDisable(true);
		btnReset.setDisable(true);
		txtFName.setText("");
		txtLName.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtTel.setText("");
	}

	public void save()
	{
		data.get(index.get()).setName(txtFName.getText());
		data.get(index.get()).setSurname(txtLName.getText());
		data.get(index.get()).setEmail(txtEmail.getText());
		data.get(index.get()).setAddress(txtAddress.getText());
		data.get(index.get()).setPhoneNumber(txtTel.getText());

		if (txtFName.getText().isEmpty())
		{
			Notifications.create().text("First name is required!").darkStyle().showError();
			return;
		}
		if (txtLName.getText().isEmpty())
		{
			Notifications.create().text("Last name is required!").darkStyle().showError();
			return;
		}
		if (txtEmail.getText().isEmpty())
		{
			Notifications.create().text("Email is required!").darkStyle().showError();
			return;
		}

		if (!resolve(EmailValidator.class).validateEmail(txtEmail.getText()))
		{
			Notifications.create().darkStyle().text("Email is invalid!").showError();
			return;
		}


		Role tempRole = new Role();
		for (Role r : roles){
			if (r.getName().equals(cbRole.getValue())){
				tempRole = r;
			}
		}

		data.get(index.get()).setRole(tempRole);

		User temp = data.get(index.get());
		try
		{
			resolve(UserRepository.class).save(temp);
			Notifications.create().text("User saved and a notification email has been send").darkStyle().showConfirm();
			resolve(Mailer.class).subject("Your account has been updated")
					.to(txtEmail.getText())
					.text("Your account has been updated!")
					.send();
		}
		catch (SQLException | MessagingException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}
	}

	public void deleteSelected()
	{
		try
		{
			resolve(UserRepository.class).delete(data.get(index.get()));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		data.remove(index.get());
		tvTable.getSelectionModel().clearSelection();
		Notifications.create().text("User removed").darkStyle().showConfirm();
	}

	public void resetPass()
	{
		User temp = data.get(index.get());
		temp.setPassword(resolve(Hasher.class).hash("prready"));//randomString()));
		try
		{
			resolve(UserRepository.class).save(temp);
			resolve(Mailer.class).subject("Your account has been updated")
					.to(txtEmail.getText())
					.text("Your password has been reset.\nMake sure you change it the next time you log in!")
					.send();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
		Notifications.create().text("Password has been reset and a notification email has been send").darkStyle().showConfirm();
	}

	public String randomString()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++)
		{
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();
	}

	public void addUser()
	{
		if (txtUsername.getText().length() < 4)
		{
			Notifications.create().text("Username must be at least 4 characters long!").darkStyle().showError();
			return;
		}

		if (data.parallelStream().filter(user -> txtUsername.getText().equals(user.getUsername())).count() == 0)
		{
			Notifications.create().text("Username already in use!").darkStyle().showError();
			return;
		}

		User temp = new User();
		temp.setUsername(txtUsername.getText());
		temp.setPassword(resolve(Hasher.class).hash("prready"));  //TODO change to random string and add email
		try
		{
			resolve(UserRepository.class).save(temp);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		data.add(temp);
		Notifications.create().text("User added").darkStyle().showConfirm();
	}
}
