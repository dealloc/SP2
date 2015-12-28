package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.engio.mbassy.listener.Handler;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.Resources.fxml;
import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class TakeQuizController implements Initializable
{
	@FXML
	private ProgressBar pbQuestions;
	@FXML
	private AnchorPane contentRoot;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		//TODO open the first question in "contentRoot"
		//TODO resolve doesn't get to the Handler
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("questionType.radioButtons.fxml"));
	}
	//TODO use "pbQuestions" so the user knows how much he still has to do
	public void previousQuestion(){
		//TODO go to the previous question and use the correct fxml file for that question type
		pbQuestions.setProgress(pbQuestions.getProgress() - 0.1);
	}

	public void nextQuestion(){
		//TODO go to the next question and use the correct fxml file for that question type
		//TODO make sure the awnser is saved in some way
		pbQuestions.setProgress(pbQuestions.getProgress() + 0.1);
	}

	public void stopQuiz(){
		//TODO quit the quiz in a safe way
		resolve(EventBus.class).fire(new SwitchScreenEvent("design/userpanel.fxml", true));
	}

	@Handler
	public void changePanel(SwitchPaneEvent event)
	{
		Parent pane = fxml(event.getLocation());
		Timeline fadein = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(pane.opacityProperty(), 0)), // TODO might produce nullpointer exception
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
