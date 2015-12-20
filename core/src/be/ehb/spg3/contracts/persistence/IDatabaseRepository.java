package be.ehb.spg3.contracts.persistence;

// Created by Wannes Gennar. All rights reserved

/**
 * Specifies a contract for interacting with global database settings.
 */
public interface IDatabaseRepository
{
	/**
	 * Initialize the database, it's connections and setup the environment to communicate.
	 */
	void initialize();

	/**
	 * Clean up all used resources and connections with the database.
	 */
	void finish();

	/**
	 * Create an object, or update it if it already exists.
	 * <br>
	 * <p>This method should not be in this contract, but due to <b>Hibernate limitations</b> There was no other way to do it.</p>
	 * @param model The model to update or create.
	 * @param <T> The type of model.
	 */
	<T> void createOrUpdate(T model);
}
