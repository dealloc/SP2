package be.ehb.spg3.contracts.entities;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.exceptions.ModelNotFoundException;

import java.util.List;

/**
 * Provides a contract for all model related repositories.
 *
 * @param <T> The model type.
 */
public interface IModelRepository<T>
{
	/**
	 * Save a new model to the database or update an existing one.
	 *
	 * @param obj The model instance to persist.
	 */
	void save(T obj);

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 */
	T find(int id);

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 */
	T findOrFail(int id) throws ModelNotFoundException;

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 */
	T findByField(String field, String value);

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 */
	T findByField(String field, int value);

	/**
	 * Get an instance of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return An intance of T matching given query, or null if none was found.
	 */
	T findByField(String field, boolean value);

	/**
	 * Return a list with all models currently persisted in the database.
	 *
	 * @return A list of persisted objects.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 */
	List<T> getAll();
}