package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.questions.QuestionRepository;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.events.QuestionAddedEvent;
import be.ehb.spg3.events.SwitchPaneEvent;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.engio.mbassy.listener.Handler;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class AddQuizController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	static ObservableList<Question> questions =  FXCollections.observableArrayList();

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
		resolve(EventBus.class).subscribe(this);

		tcName.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		tcType.setCellValueFactory(new PropertyValueFactory<Question, String>("type"));
		tvQuestions.setItems(questions);

		System.out.println("\nObservalbe list 'questions' at initialization of addquiz: ");
		for (Question q : questions){
			System.out.println(q.getQuestion());
		}

		tvQuestions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				index.set(questions.indexOf(newValue));
			}
		});
	}

	@Handler
	public void addQuestion(QuestionAddedEvent event)
	{
		// some controller created a question, add it to the quiz
		questions.add(event.getQuestion());
		System.out.println("\nYou added: " + event.getQuestion().getQuestion());
		System.out.println("\nObservalbe list 'questions': ");
		for (Question q : questions){
			System.out.println(q.getQuestion());
		}
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
		questions.remove(index.get());
	}

	public void create()
	{
		if (questions.isEmpty()){
			Notifications.create().text("You need at least 1 question!").darkStyle().showError();
			return;
		}

		if (txtQuizName.getText().isEmpty()){
			Notifications.create().text("Fill in your quiz name!").darkStyle().showError();
			return;
		}

		Quiz newQuiz = new Quiz();
		newQuiz.setName(txtQuizName.getText());
		newQuiz.setGroup(resolve(Authenticator.class).auth().getGroup());
		newQuiz.setOwner(resolve(Authenticator.class).auth());
		try
		{
			resolve(QuizRepository.class).save(newQuiz);
		}
		catch (SQLException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}
	}
}
