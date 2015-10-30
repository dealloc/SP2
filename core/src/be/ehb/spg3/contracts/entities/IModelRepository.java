package be.ehb.spg3.contracts.entities;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.exceptions.ConnectivityException;
import be.ehb.spg3.exceptions.ModelNotFoundException;
import be.ehb.spg3.exceptions.QueryException;

import java.util.List;

/**
 * Provides a contract for all model related repositories.
 *
 * @param <T> The model type.
 */
public interface IModelRepository<T>
{
	/**
	 * Save a new model to the databases or update an existing one.
	 *
	 * @param obj The model instance to persist.
	 */
	void save(T obj) throws QueryException, ConnectivityException;

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 */
	T find(int id) throws QueryException, ConnectivityException;

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 */
	T findOrFail(int id) throws ModelNotFoundException, QueryException, ConnectivityException;

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 */
	List<T> findByField(String field, String value) throws QueryException, ConnectivityException;

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 */
	List<T> findByField(String field, int value) throws QueryException, ConnectivityException;

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 */
	List<T> findByField(String field, boolean value) throws QueryException, ConnectivityException;

	/**
	 * Return a list with all models currently persisted in the databases.
	 *
	 * @return A list of persisted objects.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 */
	List<T> getAll() throws QueryException, ConnectivityException;
}