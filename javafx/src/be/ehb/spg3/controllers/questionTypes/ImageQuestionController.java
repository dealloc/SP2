package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.questions.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class ImageQuestionController extends BaseAnswerController
{
	@FXML
	private Label lblQuestion;
	@FXML
	private FlowPane answerPane;
	@FXML
	private ImageView imgView;

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
		Image img = new Image(question.getMediaUrl());
		imgView.setImage(img);
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
}
