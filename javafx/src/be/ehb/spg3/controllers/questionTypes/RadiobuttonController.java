package be.ehb.spg3.controllers.questionTypes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RadiobuttonController implements Initializable
{
	@FXML
	private FlowPane awnserPane;
	@FXML
	private Label lblQuestion;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO add a radiobutton for each awnser (make sure you know which awnser is the correct one)
		//TODO save awnser and know if it's correct or not
	}
}
