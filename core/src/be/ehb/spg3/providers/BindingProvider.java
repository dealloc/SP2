package be.ehb.spg3.providers;

import be.ehb.spg3.auth.AuthRepository;
import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.validation.EmailValidator;
import be.ehb.spg3.encryption.DummyCryptor;
import be.ehb.spg3.events.MBassadorBus;
import be.ehb.spg3.validation.ValidationRepository;
import com.google.inject.AbstractModule;
import com.j256.ormlite.support.ConnectionSource;

// Created by Wannes Gennar. All rights reserved

/**
 * Binds all contracts to their respective implementations.
 */
class BindingProvider extends AbstractModule
{
	/**
	 * Configures the bindings.
	 * This method is called by Guice itself.
	 */
	@Override
	protected void configure()
	{
		initAuth();
		initBusses();
		initConnections();
		initValidators();
	}

	private void initAuth()
	{
		AuthRepository authenticator = new AuthRepository();
		bind(Authenticator.class).toInstance(authenticator);
		bind(Authorizator.class).toInstance(authenticator);
		//bind(Encryptor.class).to(PlainCryptor.class);
		bind(Encryptor.class).to(DummyCryptor.class);
	}

	private void initBusses()
	{
		bind(EventBus.class).toInstance(new MBassadorBus());
	}

	private void initConnections()
	{
		bind(ConnectionSource.class).toProvider(ConnectionProvider.class);
	}

	private void initValidators()
	{
		bind(EmailValidator.class).to(ValidationRepository.class);
	}
}
