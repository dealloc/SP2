package be.ehb.spg3.providers;

import be.ehb.spg3.auth.AuthRepository;
import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.encryption.PlainCryptor;
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
		initConnections();
	}

	private void initAuth()
	{
		AuthRepository authenticator = new AuthRepository();
		bind(Authenticator.class).toInstance(authenticator);
		bind(Authorizator.class).toInstance(authenticator);
		bind(Encryptor.class).to(PlainCryptor.class);
	}

	private void initConnections()
	{
	}
}
