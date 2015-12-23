package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.fx.PrReadyApplication;
import be.ehb.spg3.fx.PrReadyPreloader;
import com.sun.javafx.application.LauncherImpl;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("launching PR ready quiz tool");
		LauncherImpl.launchApplication(PrReadyApplication.class, PrReadyPreloader.class, args); // We have to go deeper!
		resolve(IDatabaseRepository.class).finish();
	}
}