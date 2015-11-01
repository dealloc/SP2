package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import be.ehb.spg3.realms.database.CoreRealm;
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
		CoreRealm.test();
		stage.setTitle("software project - 3");
		stage.show();
	}
}