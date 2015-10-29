package be.ehb.spg3.providers;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.repositories.auth.AuthRepository;
import com.google.inject.AbstractModule;

// Created by Wannes Gennar. All rights reserved
class BindingProvider extends AbstractModule
{
	@Override
	protected void configure()
	{
		AuthRepository authenticator = new AuthRepository();
		bind(Authenticator.class).toInstance(authenticator);
		bind(Authorizator.class).toInstance(authenticator);
	}
}
