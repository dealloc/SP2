package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("design/moderator/dashboard.fxml"));
		primaryStage.setScene(new Scene(root, 1280, 720));
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.setResizable(false);
		//primaryStage.centerOnScreen();
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}