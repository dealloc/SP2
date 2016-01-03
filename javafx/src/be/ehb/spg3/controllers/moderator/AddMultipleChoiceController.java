package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.questions.QuestionType;
import be.ehb.spg3.events.PopupEvent;
import be.ehb.spg3.events.QuestionAddedEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.net.URL;
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
		Answer a1 = new Answer(txtAnswer1.getText(), cbCorrect1.isSelected());
		q.addAnswer(a1);
		Answer a2 = new Answer(txtAnswer2.getText(), cbCorrect2.isSelected());
		q.addAnswer(a2);
		if (!txtAnswer3.getText().isEmpty())
		{
			Answer a3 = new Answer(txtAnswer3.getText(), cbCorrect3.isSelected());
			q.addAnswer(a3);
		}
		if (!txtAnswer4.getText().isEmpty())
		{
			Answer a4 = new Answer(txtAnswer4.getText(), cbCorrect4.isSelected());
			q.addAnswer(a4);
		}

		resolve(EventBus.class).fire(new QuestionAddedEvent(q));
	}

	public void cancel()
	{
		resolve(EventBus.class).fire(new PopupEvent.ClosePopupEvent());
	}
}
