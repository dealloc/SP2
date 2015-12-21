package be.ehb.spg3.providers;

import be.ehb.spg3.auth.AuthRepository;
import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.contracts.encryption.Hasher;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.mailing.Mailer;
import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.contracts.validation.EmailValidator;
import be.ehb.spg3.contracts.validation.StringValidator;
import be.ehb.spg3.encryption.Sha1Cryptor;
import be.ehb.spg3.events.MBassadorBus;
import be.ehb.spg3.mailing.GMailer;
import be.ehb.spg3.persistence.ModelDatabaseRepository;
import be.ehb.spg3.validation.ValidationRepository;
import com.google.inject.AbstractModule;

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
		bind(Hasher.class).to(Sha1Cryptor.class);
	}

	private void initBusses()
	{
		bind(EventBus.class).toInstance(new MBassadorBus());
	}

	private void initConnections()
	{
		bind(IDatabaseRepository.class).toInstance(new ModelDatabaseRepository());
	}

	private void initValidators()
	{
		bind(EmailValidator.class).to(ValidationRepository.class);
		bind(StringValidator.class).to(ValidationRepository.class);
		bind(Mailer.class).to(GMailer.class);
	}
}
