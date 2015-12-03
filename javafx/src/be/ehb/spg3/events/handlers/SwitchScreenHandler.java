package be.ehb.spg3.events.handlers;

import be.ehb.spg3.events.SwitchScreenEvent;
import com.guigarage.flatterfx.FlatterFX;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.engio.mbassy.listener.Handler;

import java.io.IOException;

// Created by Wannes Gennar. All rights reserved
public class SwitchScreenHandler
{
	private Stage stage;
	private SwitchScreenEvent event;

	public SwitchScreenHandler(Stage stage)
	{
		this.stage = stage;
	}

	@Handler
	public void handle(SwitchScreenEvent event)
	{
		this.event = event;
		Platform.runLater(this::showWindow);
	}

	private void showWindow()
	{
		try
		{
			Parent root = FXMLLoader.load(event.getLocation()); // attempt to load first. if this fails nothing changes
			if (this.stage.isShowing())
			{
				this.stage.close();
				this.stage = new Stage();
			}

			Scene myScene = new Scene(root);
			stage.setTitle("Software Project 2 - Groep 3");
			if (event.getFullscreen()) this.setFullscreen();
			else this.setWindowed();
			stage.setScene(myScene);
			stage.show();

			FlatterFX.style();
		}
		catch (IOException io)
		{
			// TODO fire event
		}
	}

	private void setFullscreen()
	{
		stage.setFullScreen(true);
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setMinWidth(800);
		stage.setMinHeight(600);
	}

	private void setWindowed()
	{
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.centerOnScreen();
	}
}
