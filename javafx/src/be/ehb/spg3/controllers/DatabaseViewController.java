package be.ehb.spg3.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

public class DatabaseViewController implements Initializable
{
	//ObservableList<String> tables = FXCollections.observableArrayList("Users", "Quizzes", "Results");

	@FXML
	private ComboBox cbTables;
	@FXML
	private Label lblTest;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		ObservableList<String> tables = FXCollections.observableArrayList("Users","Quizzes","Results");
		cbTables.setItems(tables);
		cbTables.setValue("Users");
		lblTest.setText("tables");
	}
}
