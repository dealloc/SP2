package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.controllers.questionTypes.BaseAnswerController;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.questions.QuestionRepository;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.events.SwitchPaneEvent;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.engio.mbassy.listener.Handler;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.Resources.controller;
import static be.ehb.spg3.Resources.fxml;
import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class TakeQuizController implements Initializable
{
	private Quiz quiz;

	@FXML
	private ProgressBar pbQuestions;
	@FXML
	private AnchorPane contentRoot;

	private List<Question> questions;
	Iterator<Question> iterator;
	int progress = 0;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		this.pbQuestions.setProgress(-1);
		resolve(EventBus.class).subscribe(this);
		this.setQuiz(QuizzesController.SELECTED_QUIZ);
	}

	public void previousQuestion()
	{
		if (progress != 0)
		{
			this.progress--;
			this.iterator = this.questions.iterator();
			for (int i = 0; i <= this.progress && this.iterator.hasNext(); i++)
				this.iterator.next();
		}
	}

	public void nextQuestion()
	{
		if (this.iterator.hasNext())
		{
			this.progress++;
			Question question = this.iterator.next();

			String page = "user.questionType.radioButtons.fxml";
			switch (question.getType())
			{
				case MultipleChoice:
					page = "user.questionType.radioButtons.fxml";
					break;
				case Image:
					break;
				case Video:
					break;
				case Audio:
					break;
			}

			resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent(page));
			((BaseAnswerController) controller()).setQuestion(question);

			this.pbQuestions.setProgress((1 / this.questions.size()) * this.progress);
		}
		else
		{
			Notifications.create().text("That's all folks").showConfirm();
		}
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

	public void setQuiz(Quiz quiz)
	{
		if (!quiz.getQuestions().isEmpty())
		{
			this.quiz = quiz;
			try
			{
				this.questions = resolve(QuestionRepository.class).findByQuiz(quiz);
				this.iterator = this.questions.iterator();
				Platform.runLater(this::nextQuestion);
			}
			catch (Exception e)
			{
				resolve(EventBus.class).fire(new ErrorEvent(e));
			}
		}
		else
		{
			Notifications.create().text("Hmm, strange. There don't seem to be questions here!").showError();
		}
	}
}