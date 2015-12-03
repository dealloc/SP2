package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.events.handlers.SwitchScreenHandler;
import javafx.application.Application;
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
		SwitchScreenHandler handler = new SwitchScreenHandler(stage);
		resolve(EventBus.class).subscribe(handler);

		resolve(EventBus.class).fire(new SwitchScreenEvent("design/admin/AdminHome.fxml", true));
//		resolve(EventBus.class).fire(new SwitchScreenEvent("design/login/login.fxml", false));
//		resolve(EventBus.class).fire(new SwitchScreenEvent("design/admin/dashboard.fxml", false));
	}
}