package be.ehb.spg3;

// Created by Jérémy Thiebaut. All rights reserved

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setTitle("PR Ready -- Login for Quizz Tool");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
}
