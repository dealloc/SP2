package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.entities.answer.Answer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class RadiobuttonController extends BaseAnswerController implements Initializable
{
	@FXML
	private FlowPane answerPane;
	@FXML
	private Label lblQuestion;

	private Answer answer = null;

	@Override
	public Answer getAnswer()
	{
		return this.answer;
	}

	@Override
	public void init()
	{
		this.lblQuestion.setText(question.getQuestion());
		Collection<Answer> answers = question.getAnswers();
		int index = 1;

		ToggleGroup group = new ToggleGroup();
		for (Answer answer : answers)
		{
			RadioButton button = new RadioButton(answer.getText());
			//button.setLayoutX(20);
			//button.setLayoutY(index++ * 25);
			//button.setToggleGroup(group);
			button.setOnAction(event -> this.answer = answer);
			this.answerPane.getChildren().add(button);
		}
		System.out.println("INIT: " + lblQuestion.getText());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		System.out.println("INITIALIZE: " + lblQuestion.getText());
	}
}
