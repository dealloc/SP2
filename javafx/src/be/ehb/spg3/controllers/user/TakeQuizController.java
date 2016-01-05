package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.controllers.questionTypes.BaseAnswerController;
import be.ehb.spg3.controllers.questionTypes.QuizOverviewController;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.questions.QuestionRepository;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.results.Result;
import be.ehb.spg3.entities.results.ResultRepository;
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
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.Resources.controller;
import static be.ehb.spg3.Resources.fxml;
import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class TakeQuizController implements Initializable
{
	private static TakeQuizController listener = null;
	private Quiz quiz;

	@FXML
	private ProgressBar pbQuestions;
	@FXML
	private AnchorPane contentRoot;

	private Result result;
	private List<Question> questions;
	Iterator<Question> iterator;
	int progress = 0;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		if (TakeQuizController.listener != null)
		{
			resolve(EventBus.class).unsubscribe(TakeQuizController.listener); // unsubscribe previous event handler to prevent it from handling events it doesn't need.
		}
		TakeQuizController.listener = this;
		this.pbQuestions.setProgress(-1);
		resolve(EventBus.class).subscribe(this);
		this.setQuiz(QuizzesController.SELECTED_QUIZ);
	}

	public void previousQuestion()
	{
		if (progress != 0)
		{
			this.progress -= 2;
			this.iterator = this.questions.iterator();
			for (int i = 0; i <= this.progress && this.iterator.hasNext(); i++)
				this.iterator.next();

			this.nextQuestion();
		}
	}

	public void nextQuestion()
	{
		if (this.iterator.hasNext())
		{
			this.progress++;
			Question question = this.iterator.next();

			String page = "user.questionType.radioButtons.fxml"; // so we never have an empty page
			switch (question.getType())
			{
				case MultipleChoice:
					page = "user.questionType.radioButtons.fxml";
					break;
				case Image:
					page = "user.questionType.imageQuestion.fxml";
					break;
				case Video:
					page = "user.questionType.videoQuestion.fxml";
					break;
				case Audio:
					page = "user.questionType.audioQuestion.fxml";
					break;
			}

			resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent(page));
			((BaseAnswerController) controller()).setQuestion(question);
		}
		else
		{
			if (controller() instanceof QuizOverviewController)
			{
				// finish quiz!
				try
				{
					resolve(ResultRepository.class).save(this.result);
					Notifications.create().text("Results saved!").showInformation();
					this.stopQuiz();
				}
				catch (SQLException e)
				{
					resolve(EventBus.class).fire(new ErrorEvent(e));
				}
			}
			else
			{
				resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("user.questionType.quizOverview.fxml"));
				((QuizOverviewController) controller()).setQuiz(this.quiz);
			}
		}

		this.pbQuestions.setProgress((1 / this.questions.size()) * this.progress);
	}

	public void stopQuiz()
	{
		//TODO save exit
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
		if (!quiz.getQuestions().isEmpty()) // Without this check we might divide by zero, brrr!
		{
			this.quiz = quiz;
			this.result = new Result(this.quiz, resolve(Authenticator.class).auth());
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
			// TODO this notification is lame
			Notifications.create().text("Hmm, strange. There don't seem to be questions here!").showError();
		}
	}
}