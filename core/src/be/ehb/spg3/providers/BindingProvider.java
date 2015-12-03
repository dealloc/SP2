package be.ehb.spg3.providers;

import be.ehb.spg3.auth.AuthRepository;
import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.auth.Authorizator;
import be.ehb.spg3.contracts.encryption.Encryptor;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.encryption.PlainCryptor;
import be.ehb.spg3.events.MBassadorBus;
import com.google.inject.AbstractModule;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

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
	}

	private void initAuth()
	{
		AuthRepository authenticator = new AuthRepository();
		bind(Authenticator.class).toInstance(authenticator);
		bind(Authorizator.class).toInstance(authenticator);
		bind(Encryptor.class).to(PlainCryptor.class);
	}

	private void initBusses()
	{
		bind(EventBus.class).toInstance(new MBassadorBus());
	}

	private void initConnections()
	{
		JdbcConnectionSource source = null; // TODO manage lifecycle of the connection to the server
		try
		{
			source = new JdbcConnectionSource("jdbc:mysql://dt5.ehb.be:3306/SP2_GR3", "SP2_GR3", "3qCxw");
		}
		catch (SQLException e)
		{
			e.printStackTrace(); // TODO handle connection
		}
		bind(ConnectionSource.class).toInstance(source);
	}
}
