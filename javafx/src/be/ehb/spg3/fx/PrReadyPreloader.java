package be.ehb.spg3.fx;

import javafx.application.Preloader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static be.ehb.spg3.Resources.fxml;

// Created by Wannes Gennar. All rights reserved
public class PrReadyPreloader extends Preloader
{
	private Stage stage;

	@Override
	public void start(Stage stage) throws Exception
	{
		this.stage = stage;
		Parent pane = fxml("preloader.preloader.fxml");
		stage.setScene(new Scene(pane));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setAlwaysOnTop(true);
		stage.show();
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info)
	{
		if (info.getType() == StateChangeNotification.Type.BEFORE_START)
		{
			this.stage.hide();
		}
	}
}
