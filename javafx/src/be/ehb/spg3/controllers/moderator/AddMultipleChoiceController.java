package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.answer.AnswerRepository;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.questions.QuestionRepository;
import be.ehb.spg3.entities.questions.QuestionType;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.events.PopupEvent;
import be.ehb.spg3.events.QuestionAddedEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class AddMultipleChoiceController implements Initializable
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

	public void addQuestion()
	{
		if (txtQuestion.getText().isEmpty())
		{
			Notifications.create().text("Fill in your question").darkStyle().showError();
			return;
		}

		if (txtAnswer1.getText().isEmpty() || txtAnswer2.getText().isEmpty())
		{
			Notifications.create().text("Answer 1 and 2 are required").darkStyle().showError();
			return;
		}

		Question q = new Question();
		q.setQuestion(txtQuestion.getText());
		q.setType(QuestionType.MultipleChoice);

		Answer a1 = new Answer(txtAnswer1.getText(), true);
		if (cbCorrect1.isSelected() == true)
		{
			a1.setCorrect(true);
			a1.setText(txtAnswer1.getText());
			try
			{
				resolve(AnswerRepository.class).save(a1);
				q.addAnswer(a1);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			a1.setCorrect(false);
			a1.setText(txtAnswer1.getText());
			try
			{
				resolve(AnswerRepository.class).save(a1);
				q.addAnswer(a1);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			q.addAnswer(a1);
		}

		Answer a2 = new Answer(txtAnswer2.getText(), true);
		if (cbCorrect2.isSelected() == true)
		{
			a2.setCorrect(true);
			a2.setText(txtAnswer2.getText());
			try
			{
				resolve(AnswerRepository.class).save(a2);
				q.addAnswer(a2);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			a2.setCorrect(false);
			a2.setText(txtAnswer2.getText());
			try
			{
				resolve(AnswerRepository.class).save(a2);
				q.addAnswer(a2);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		if (!txtAnswer3.getText().isEmpty())
		{
			Answer a3 = new Answer(txtAnswer3.getText(), true);
			if (cbCorrect3.isSelected() == true)
			{
				a3.setCorrect(true);
				a3.setText(txtAnswer3.getText());
				try
				{
					resolve(AnswerRepository.class).save(a3);
					q.addAnswer(a3);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			} else
			{
				a3.setCorrect(false);
				a3.setText(txtAnswer3.getText());
				try
				{
					resolve(AnswerRepository.class).save(a3);
					q.addAnswer(a3);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		if (!txtAnswer4.getText().isEmpty())
		{
			Answer a4 = new Answer(txtAnswer4.getText(), cbCorrect4.isSelected());
			if (cbCorrect4.isSelected() == true)
			{
				a4.setCorrect(true);
				a4.setText(txtAnswer4.getText());
				try
				{
					resolve(AnswerRepository.class).save(a4);
					q.addAnswer(a4);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			} else
			{
				a4.setCorrect(false);
				a4.setText(txtAnswer4.getText());
				try
				{
					resolve(AnswerRepository.class).save(a4);
					q.addAnswer(a4);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		resolve(EventBus.class).fire(new QuestionAddedEvent(q));
	}

	public void cancel()
	{
		resolve(EventBus.class).fire(new PopupEvent.ClosePopupEvent());
	}
}
