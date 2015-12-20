package be.ehb.spg3.contracts.entities;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.exceptions.ModelNotFoundException;

import java.sql.SQLException;
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
	 * @throws SQLException When an error occured.
	 */
	void save(T obj) throws SQLException;

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 * @throws SQLException When an error occured.
	 */
	T find(long id) throws SQLException;

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 * @throws SQLException           When an SQL error occured.
	 */
	T findOrFail(long id) throws ModelNotFoundException, SQLException;

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occured.
	 */
	List<T> findByField(String field, String value) throws SQLException;

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occured.
	 */
	List<T> findByField(String field, int value) throws SQLException;

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occured.
	 */
	List<T> findByField(String field, boolean value) throws SQLException;

	/**
	 * Get instances of T by searching given fields
	 *
	 * @param fields The fields to query
	 * @return A list of values matching given fields or null if an error was thrown
	 * @throws SQLException When an error occured.
	 */
	List<T> findByFields(String[]... fields) throws SQLException;

	/**
	 * Return a list with all models currently persisted in the databases.
	 *
	 * @return A list of persisted objects.
	 * @throws SQLException When an error occured.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 */
	List<T> getAll() throws SQLException;

	/**
	 * Create the associated table if it does not exist yet.
	 *
	 * @throws SQLException When an error occured.
	 */
	void createIfNotExists() throws SQLException;

	/**
	 * Create an instance of the model and set it's auto incrementing ID etc
	 *
	 * @return An instance of T
	 * @throws SQLException
	 */
	T create() throws SQLException;

	/**
	 * Remove a subject from the database.
	 *
	 * @param subject The subject to remove.
	 * @throws SQLException When an error occured.
	 */
	void delete(T subject) throws SQLException;
}