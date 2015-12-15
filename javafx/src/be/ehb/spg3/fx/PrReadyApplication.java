package be.ehb.spg3.fx;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.events.handlers.SwitchScreenHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved
public class PrReadyApplication extends Application
{
	SwitchScreenHandler handler;

	@Override
	public void init() throws Exception
	{
		// TODO heavy lifting
		Thread.sleep(3000);
		System.out.println("done initializing pr app");
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		stage.show();
		handler = new SwitchScreenHandler(stage);
		resolve(EventBus.class).subscribe(handler);

		resolve(EventBus.class).fire(new SwitchScreenEvent("design/login/login.fxml", false));
	}
}