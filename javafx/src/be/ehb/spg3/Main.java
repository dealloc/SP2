package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import be.ehb.spg3.contracts.threading.ThreadPool;
import be.ehb.spg3.threading.ThreadManager;
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
		ThreadPool pool = new ThreadManager();
		pool.submit(() -> System.out.println("hello from threads!"));

		stage.setTitle("software project - 3");
		stage.show();
	}
}
