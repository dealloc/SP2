package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		stage.setTitle(Dummy.dummy());
		stage.show();
	}
}
