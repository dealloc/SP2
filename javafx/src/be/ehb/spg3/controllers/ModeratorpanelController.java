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
		this.manageQuizzes();
	}

	public void close()
	{
		Platform.exit();
	}

	//All methods for moderatorpanel.fxml
	public void editProfile()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("editProfile.fxml"));
	}

	public void createQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator/addquiz.fxml"));
	}

	public void manageQuizzes()	{resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.userResults.fxml"));}

	public void manageGroup()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.manageGroups.fxml"));
	}

	public void addMultipleChoice (){
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addMultipleChoice.fxml"));
	}

	public void addImage (){
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addImageQuestion.fxml"));
	}

	public void addVideo(){
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addVideoQuestion.fxml"));
	}

	public void addAudio(){
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.addAudioQuestion.fxml"));
	}

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