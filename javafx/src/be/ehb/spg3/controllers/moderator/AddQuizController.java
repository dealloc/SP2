package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.controllers.ModeratorpanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQuizController implements Initializable
{
	//ObservableList<String> questionTypes = FXCollections.observableArrayList("Multiple choice", "Image", "Audio", "Video");

	@FXML
	private TextField txtQuizName;
	@FXML
	private TableView tvQuestions;
	@FXML
	private TableColumn tcName;
	@FXML
	private TableColumn tcType;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO tableview management
	}

	public void addMultipleChoiceQuestion()
	{
		new ModeratorpanelController().addMultipleChoice();
	}

	public void addImageQuestion()
	{
		new ModeratorpanelController().addImage();
	}

	public void addAudioQuestion()
	{
		new ModeratorpanelController().addAudio();
	}

	public void addVideoQuestion()
	{
		new ModeratorpanelController().addVideo();
	}

	public void removeQuestion()
	{
		//TODO remove the selected question
	}

	public void create()
	{
		//TODO save quiz with its questions
	}
}
