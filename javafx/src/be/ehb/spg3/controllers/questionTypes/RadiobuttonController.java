package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.questions.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RadiobuttonController implements Initializable
{
	@FXML
	private FlowPane answerPane;
	@FXML
	private Label lblQuestion;
	private Question question;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO add a radiobutton for each awnser (make sure you know which awnser is the correct one)
		//TODO save awnser and know if it's correct or not
	}

	public void setQuestion(Question question)
	{
		this.question = question;
		for (Answer answer : question.getAnswers())
		{
			RadioButton button = new RadioButton(answer.getText());
			this.answerPane.getChildren().add(button);
		}
	}
}
