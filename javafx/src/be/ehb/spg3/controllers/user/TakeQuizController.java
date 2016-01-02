package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.controllers.questionTypes.RadiobuttonController;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.questions.QuestionRepository;
import be.ehb.spg3.entities.questions.QuestionType;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.events.SwitchPaneEvent;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.engio.mbassy.listener.Handler;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.Resources.controller;
import static be.ehb.spg3.Resources.fxml;
import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class TakeQuizController implements Initializable
{
	@FXML
	private ProgressBar pbQuestions;
	@FXML
	private AnchorPane contentRoot;

	private List<Question> questions;
	private int index = -1;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		this.pbQuestions.setProgress(-1);
		resolve(EventBus.class).subscribe(this);

		try
		{
			Quiz quiz = resolve(QuizRepository.class).find(1);
			this.questions = resolve(QuestionRepository.class).findByQuiz(quiz);
			this.nextQuestion();
		}
		catch (SQLException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}
	}

	public void previousQuestion()
	{
		this.pbQuestions.setProgress(this.index);
	}

	public void nextQuestion()
	{
		if (++this.index < this.questions.size())
		{
			Question question = this.questions.get(this.index);
			if (question.getType() == QuestionType.MultipleChoice)
			{
				resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("user.questionType.radioButtons.fxml"));
				((RadiobuttonController) controller()).setQuestion(question);
			}
		}
		else
		{
			// that was the last question
		}

		this.pbQuestions.setProgress(this.index);
	}

	public void stopQuiz()
	{
		resolve(EventBus.class).fire(new SwitchScreenEvent("design/userpanel.fxml", true));
	}

	@Handler
	public void changePanel(SwitchPaneEvent event)
	{
		Parent pane = fxml(event.getLocation());
		if (pane != null)
		{
			Timeline fadein = new Timeline(
					                              new KeyFrame(Duration.ZERO, new KeyValue(pane.opacityProperty(), 0)),
					                              new KeyFrame(Duration.seconds(1), new KeyValue(pane.opacityProperty(), 1))
			);
			this.contentRoot.getChildren().clear();
			this.contentRoot.getChildren().add(pane);
			AnchorPane.setTopAnchor(pane, 0.0);
			AnchorPane.setRightAnchor(pane, 0.0);
			AnchorPane.setLeftAnchor(pane, 0.0);
			AnchorPane.setBottomAnchor(pane, 0.0);
			fadein.play();
		}
	}
}
