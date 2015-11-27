package be.ehb.spg3.entities;

import be.ehb.spg3.contracts.entities.IModelRepository;
import be.ehb.spg3.exceptions.ConnectivityException;
import be.ehb.spg3.exceptions.ModelNotFoundException;
import be.ehb.spg3.exceptions.QueryException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

// Created by Wannes Gennar. All rights reserved

/**
 * The BaseModelRepository is a base class for all model specific modelrepositories.
 * It provides generic handling of CRUD operations and some simple queries.
 *
 * @param <T> The model class to use.
 * @todo make BaseModelRepository an abstract class.
 */
public abstract class BaseModelRepository<T> implements IModelRepository<T>
{
	private Dao<T, Integer> dao;

	public BaseModelRepository(Class<T> model) throws SQLException
	{
		// initialize connection to localhost on port 3306 to table spg3 TODO refactor!
		ConnectionSource connection = new JdbcConnectionSource("jdbc:mysql://dt5.ehb.be:3306/SP2_GR3", "SP2_GR3", "3qCxw");
		this.dao = DaoManager.createDao(connection, model);
		TableUtils.createTableIfNotExists(connection, model); // TODO refactor to another repository!
	}

	/**
	 * Save a new model to the databases or update an existing one.
	 *
	 * @param obj The model instance to persist.
	 * @todo check if id is default (which means it doesn't exist) and generate proper ID. (ormlite puts it all on 0)
	 * @todo check if obj is null and throw InvalidArgumentException
	 * @todo forward proper exceptions
	 */
	@Override
	public void save(T obj) throws QueryException, ConnectivityException
	{
		try
		{
			this.dao.createOrUpdate(obj);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 * @todo forward proper exceptions
	 */
	@Override
	public T find(int id) throws QueryException, ConnectivityException
	{
		try
		{
			return this.dao.queryForId(id);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 * @todo forward proper exceptions
	 */
	@Override
	public T findOrFail(int id) throws ModelNotFoundException, QueryException, ConnectivityException
	{
		try
		{
			T result = this.dao.queryForId(id);
			if (result != null) return result;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		throw new ModelNotFoundException("No results found for " + id);
	}

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @todo forward proper exceptions
	 */
	@Override
	public List<T> findByField(String field, String value) throws QueryException, ConnectivityException
	{
		try
		{
			return this.dao.queryForEq(field, value);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 * @todo forward proper exceptions
	 */
	@Override
	public List<T> findByField(String field, int value) throws QueryException, ConnectivityException
	{
		try
		{
			return this.dao.queryForEq(field, value);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 * @todo forward proper exceptions
	 */
	@Override
	public List<T> findByField(String field, boolean value) throws QueryException, ConnectivityException
	{
		try
		{
			return this.dao.queryForEq(field, value);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get instances of T by searching given fields
	 *
	 * @param fields The fields to query
	 * @return A list of values matching given fields or null if an error was thrown
	 * @throws QueryException        When an SQL error occured.
	 * @throws ConnectivityException When there was an error connecting to the database.
	 * @todo forward proper exceptions
	 */
	public List<T> findByFields(String[]... fields) throws QueryException, ConnectivityException
	{
		try
		{
			Where<T, Integer> builder = this.dao.queryBuilder().where();
			for (int i = 0; i < fields.length; i++)
			{
				String[] field = fields[i];
				if (field.length < 2)
					throw new QueryException("Invalid field specification length");

				builder = (i == 0 ? builder : builder.and()).eq(field[0], field[1]);
			}

			return this.dao.query(builder.prepare());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Return a list with all models currently persisted in the databases.
	 *
	 * @return A list of persisted objects.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 * @todo forward proper exceptions
	 */
	@Override
	public List<T> getAll() throws QueryException, ConnectivityException
	{
		try
		{
			return this.dao.queryForAll();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
