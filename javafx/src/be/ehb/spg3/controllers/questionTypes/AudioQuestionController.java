package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.entities.answer.Answer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class AudioQuestionController extends BaseAnswerController implements Initializable
{
	@FXML
	private Label lblQuestion;
	@FXML
	private FlowPane answerPane;
	@FXML
	private WebView audio;

	private Answer answer;

	@Override
	public Answer getAnswer()
	{
		return this.answer;
	}

	@Override
	public void init()
	{
		this.lblQuestion.setText(question.getQuestion());
		audio.getEngine().load(question.getMediaUrl());
		Collection<Answer> answers = question.getAnswers();
		int index = 1;
		ToggleGroup group = new ToggleGroup();
		for (Answer answer : answers)
		{
			RadioButton button = new RadioButton(answer.getText());
			button.setLayoutX(20);
			button.setLayoutY(index++ * 25);
			button.setToggleGroup(group);
			button.setOnAction(event -> this.answer = answer);
			this.answerPane.getChildren().add(button);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		System.out.println("Do something to waste time, maybe this helps loading the question lol");
	}
}
