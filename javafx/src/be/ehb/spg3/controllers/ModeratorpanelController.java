/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.engio.mbassy.listener.Handler;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.Resources.fxml;
import static be.ehb.spg3.providers.InjectionProvider.resolve;


public class ModeratorpanelController implements Initializable
{

	@FXML
	public AnchorPane contentRoot;
	@FXML
	private Label lblUserName;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		String username = resolve(Authenticator.class).auth().getUsername();
		this.lblUserName.setText(username);
		resolve(EventBus.class).subscribe(this); // register ourselves as an event listener
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.dashboard.fxml"));
	}

	public void close()
	{
		Platform.exit();
	}

	//All methods for moderatorpanel.fxml
	public void editProfile()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("editprofile.fxml"));
		//TODO add functionality
	}

	public void dashboard()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.dashboard.fxml"));
		//TODO add functionality
	}

	public void createQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_new.fxml"));
		//TODO add functionality
	}

	public void manageQuizzes()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_editQuiz.fxml"));
		//TODO add functionality
	}

	public void manageGroup()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.manageGroup.fxml"));
		//TODO add functionality
	}

	public void settings()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("settings.fxml"));
		//TODO add functionality
	}

	//All methods for moderator.quizTool.fxml
	public void addQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_new.fxml"));
		//TODO add functionality
	}

	public void editQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_editQuiz.fxml"));
		//TODO add functionality
	}

	public void deleteQuiz()
	{
		//TODO add functionality
	}

	public void assignUsers()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_assignUsers.fxml"));
		//TODO add functionality
	}

	public void disableQuiz()
	{
		//TODO add functionality
	}

	public void enableQuiz()
	{
		//TODO add functionality
	}

	public void showResults()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_results.fxml"));
		//TODO add functionalitys
	}

	public void importQuiz()
	{
		//TODO add functionality
	}

	public void exportQuiz()
	{
		//TODO add functionality
	}

	//All methods for moderator.quizTool_addQuiz_new.fxml
	public void questionType()
	{
		//TODO add functionality
	}

	public void newQuestion()
	{
		//open juiste scherm naargelang welke vraag er wordt geselecteerd in de combobox (if tests)

		//open vraag
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuestion.fxml"));

		//multiple choice vraag
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_multipleChoice.fxml"));

		//multimedia vraag met multiple choice (text)antwoord
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_mediaQuestion.fxml"));

		//multimedia vraag met multiple choice (media)antwoord
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_mediaQuestion(2).fxml"));
	}

	public void save()
	{
		//TODO add functionality
	}

	//All methods for moderator.quizTool_editQuiz.fxml
	public void editQuestion()
	{
		//open juiste scherm naargelang welke vraag er wordt geselecteerd in de tableview (if tests)

		//open vraag
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuestion.fxml"));

		//multiple choice vraag
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_multipleChoice.fxml"));

		//multimedia vraag met multiple choice (text)antwoord
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_mediaQuestion.fxml"));

		//multimedia vraag met multiple choice (media)antwoord
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_mediaQuestion(2).fxml"));
		//TODO add functionality
	}

	//public void questionType(){zelfde als vorige functie (alle question types toevoegen)}

	public void addQuestion()
	{
		//open juiste scherm naargelang welke vraag er wordt geselecteerd in de combobox (if tests)

		//open vraag
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuestion.fxml"));

		//multiple choice vraag
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_multipleChoice.fxml"));

		//multimedia vraag met multiple choice (text)antwoord
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_mediaQuestion.fxml"));

		//multimedia vraag met multiple choice (media)antwoord
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.quizTool_addQuiz_mediaQuestion(2).fxml"));
	}

	public void deleteQuestion()
	{
		//TODO add functionality
	}

	//save functie

	//All methods for moderator.quizTool_assignUsers.fxml
	public void adduser()
	{
		//TODO add functionality
	}

	public void deleteUser()
	{
		//TODO add functionality
	}

	//save functie


	@Handler
	public void changePanel(SwitchPaneEvent event)
	{
		Parent pane = fxml(event.getLocation());
		if (pane != null)
		{
			Timeline fadein = new Timeline(
					                              new KeyFrame(Duration.ZERO, new KeyValue(pane.opacityProperty(), 0)),
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
}