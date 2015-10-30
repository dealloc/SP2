package be.ehb.spg3.repositories.models;

import be.ehb.spg3.contracts.entities.IModelRepository;
import be.ehb.spg3.exceptions.ConnectivityException;
import be.ehb.spg3.exceptions.ModelNotFoundException;
import be.ehb.spg3.exceptions.QueryException;

import java.util.List;

// Created by Wannes Gennar. All rights reserved

/**
 * The BaseModelRepository is a base class for all model specific modelrepositories.
 * It provides generic handling of CRUD operations and some simple queries.
 *
 * @param <T> The model class to use.
 */
public class BaseModelRepository<T> implements IModelRepository<T>
{
	/**
	 * Save a new model to the databases or update an existing one.
	 *
	 * @param obj The model instance to persist.
	 */
	@Override
	public void save(T obj) throws QueryException, ConnectivityException
	{

	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 */
	@Override
	public T find(int id) throws QueryException, ConnectivityException
	{
		return null;
	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 */
	@Override
	public T findOrFail(int id) throws ModelNotFoundException, QueryException, ConnectivityException
	{
		return null;
	}

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 */
	@Override
	public T findByField(String field, String value) throws QueryException, ConnectivityException
	{
		return null;
	}

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 */
	@Override
	public T findByField(String field, int value) throws QueryException, ConnectivityException
	{
		return null;
	}

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 */
	@Override
	public T findByField(String field, boolean value) throws QueryException, ConnectivityException
	{
		return null;
	}

	/**
	 * Return a list with all models currently persisted in the databases.
	 *
	 * @return A list of persisted objects.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 */
	@Override
	public List<T> getAll() throws QueryException, ConnectivityException
	{
		return null;
	}
}
