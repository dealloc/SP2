/*
 * Copyright (c) 2015 Jeroen Van den Broeck. All rights reserved.
 */

package be.ehb.spg3.controllers;

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


public class UserpanelController implements Initializable
{

	@FXML
	public AnchorPane contentRoot;
	@FXML
	private Label lblUserName;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		String username = "*EDIT*";//resolve(Authenticator.class).auth().getUsername();
		this.lblUserName.setText(username);
		resolve(EventBus.class).subscribe(this); // register ourselves as an event listener
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("admin.dashboard.fxml"));
	}

	public void close()
	{
		Platform.exit();
	}

	public void dashboard()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("admin.dashboard.fxml"));
	}

	public void quizzes()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("admin.usertable.fxml"));
	}

	public void profiles()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("admin.editProfile.fxml"));
	}

	public void settings()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("admin.settings.fxml"));
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