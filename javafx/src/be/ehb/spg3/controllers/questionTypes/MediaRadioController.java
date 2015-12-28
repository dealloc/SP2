package be.ehb.spg3.controllers.questionTypes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MediaRadioController implements Initializable
{
	@FXML
	private Label lblQuestion;
	@FXML
	private FlowPane awnserPane;
	@FXML
	private FlowPane mediaPane;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO update "lblQuistion" text, add radiobuttons in "awnserPane" and add the media in "mediaPane"
	}
}
