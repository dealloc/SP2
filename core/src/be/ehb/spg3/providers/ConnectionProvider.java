package be.ehb.spg3.providers;

import com.google.inject.Provider;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class ConnectionProvider implements Provider<ConnectionSource>
{
	private ConnectionSource connection = null;

	@Override
	public ConnectionSource get()
	{
		if (this.connection == null || !this.connection.isOpen()) // TODO check if connection is still alive
			this.openConnection();

		return this.connection;
	}

	private void openConnection()
	{
		try
		{
			this.connection = new JdbcConnectionSource("jdbc:mysql://dt5.ehb.be:3306/SP2_GR3", "SP2_GR3", "3qCxw");
		}
		catch (SQLException e)
		{
			e.printStackTrace(); // TODO handle exceptions
		}
	}
}
