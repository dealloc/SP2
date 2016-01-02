package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

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
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addMultipleChoice.fxml"));
	}

	public void addImageQuestion()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addImageQuestion.fxml"));
	}

	public void addAudioQuestion()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addAudioQuestion.fxml"));
	}

	public void addVideoQuestion()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addVideoQuestion.fxml"));
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
