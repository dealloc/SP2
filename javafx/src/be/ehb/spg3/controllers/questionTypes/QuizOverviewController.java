package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.quizzes.Quiz;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class QuizOverviewController
{
	@FXML
	private FlowPane flowOverview;

	public void setQuiz(Quiz quiz)
	{
		int index = 0;
		for (Question question : quiz.getQuestions())
		{
			Label label = new Label(question.getQuestion());
			label.setLayoutX(20);
			label.setLayoutY(index++ * 25);
			this.flowOverview.getChildren().add(label);
		}
	}
}
