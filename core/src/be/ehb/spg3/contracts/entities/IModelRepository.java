package be.ehb.spg3.contracts.entities;

// Created by Wannes Gennar. All rights reserved

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
	T getById(int id);

	/**
	 * Return a list with all models currently persisted in the database.
	 *
	 * @return A list of persisted objects.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 */
	List<T> getAll();
}