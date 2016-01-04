package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class QuizOverviewController implements Initializable
{
	@FXML
	private FlowPane flowOverview;

	List<Question> questions;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO get the current quiz
		//TODO get all questions and check if they are answered or not (set hyperlink color for state)

		//Hardcoded example
		List<String> questions = new ArrayList<String>();
		String question1 = "Vraag 1?";
		questions.add(question1);
		String question2 = "Vraag 2?";
		questions.add(question2);
		String question3 = "Vraag 3?";
		questions.add(question3);
		String question4 = "Vraag 4?";
		questions.add(question4);
		String question5 = "Vraag 5?";
		questions.add(question5);

		for (int i = 0; i < 4; i++)
		{
			Hyperlink hyperlink = new Hyperlink(questions.get(i));
			flowOverview.getChildren().add(hyperlink);
		}
	}

	public void submit()
	{
		//TODO save results
		resolve(EventBus.class).fire(new SwitchScreenEvent("design/userpanel.fxml", true));
	}
}
