package be.ehb.spg3.providers;

import com.google.inject.Provider;

import java.sql.Connection;
import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
class ConnectionProvider implements Provider<Connection>
{
	private Connection connection = null;

	@Override
	public Connection get()
	{
		try
		{
			if (this.connection == null || this.connection.isClosed()) // TODO check if connection is still alive
			{
				this.openConnection();
			}

			return this.connection;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private void openConnection()
	{
	}
}
