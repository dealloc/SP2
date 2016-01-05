package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.Collection;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

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
		if (img.isError())
		{
			resolve(EventBus.class).fire(new ErrorEvent(img.getException()));
		}
		else
		{
			imgView.setImage(img);
		}
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
