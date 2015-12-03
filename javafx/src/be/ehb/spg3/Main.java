package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.events.handlers.SwitchScreenHandler;
import com.guigarage.flatterfx.FlatterFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import static be.ehb.spg3.providers.InjectionProvider.resolve;


public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
//		Parent root = FXMLLoader.load(getClass().getResource("auth/login/login.fxml"));
//		stage.initStyle(StageStyle.UNDECORATED);
//		stage.setResizable(false);
//		stage.centerOnScreen();
//		stage.setScene(new Scene(root, 618, 480));
//		stage.show();

		resolve(EventBus.class).subscribe(new SwitchScreenHandler());

		resolve(EventBus.class).fire(new SwitchScreenEvent("design/Admin/AdminHome.fxml", false));

		Parent root = FXMLLoader.load(getClass().getResource("design/Admin/AdminHome.fxml"));
		Scene myScene = new Scene(root);
		stage.setTitle("Software Project 2 - Groep 3");
		stage.setScene(myScene);
		stage.setFullScreen(true);
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.show();

		FlatterFX.style();

	}
}