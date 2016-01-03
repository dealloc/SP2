package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class EditQuizController implements Initializable
{
	@FXML
	private TableView tvQuiz;
	@FXML
	private TableColumn tcQuiz;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO load his quizzes in table

	}

	public void editSelectedQuiz(){
		//TODO when quiz is selected go to edit page
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_editQuiz.fxml"));
	}
}
