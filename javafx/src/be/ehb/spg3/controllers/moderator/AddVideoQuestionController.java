package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.controllers.ModeratorpanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddVideoQuestionController implements Initializable
{
	@FXML
	private TextField txtQuestion;
	@FXML
	private TextField txtAnswer1;
	@FXML
	private TextField txtAnswer2;
	@FXML
	private TextField txtAnswer3;
	@FXML
	private TextField txtAnswer4;
	@FXML
	private CheckBox cbCorrect1;
	@FXML
	private CheckBox cbCorrect2;
	@FXML
	private CheckBox cbCorrect3;
	@FXML
	private CheckBox cbCorrect4;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public void selectVideo(){
		//TODO load video
	}

	public void addQuestion(){
		//TODO check fields and create question
	}

	public void cancel(){
		new ModeratorpanelController().createQuiz();
	}
}
