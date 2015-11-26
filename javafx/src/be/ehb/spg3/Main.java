package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved

//Branch jeroen test

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application

{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("design/Admin/AdminHome.fxml"));
		Scene myScene = new Scene(root, 1280, 720);
		//myScene.getStylesheets().add("design/Admin/style.css");
		stage.setTitle("Software Project 2 - Groep 3");
		stage.setScene(myScene);
		stage.setFullScreen(true);
		//stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.show();
	}
}