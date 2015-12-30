package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.events.handlers.ErrorHandler;
import be.ehb.spg3.fx.PrReadyApplication;
import be.ehb.spg3.fx.PrReadyPreloader;
import com.sun.javafx.application.LauncherImpl;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class Main
{
	public static void main(String[] args)
	{
		ErrorHandler errorHandler = new ErrorHandler();
		resolve(EventBus.class).subscribe(errorHandler);

		LauncherImpl.launchApplication(PrReadyApplication.class, PrReadyPreloader.class, args); // We have to go deeper!
		resolve(IDatabaseRepository.class).finish();
	}
}