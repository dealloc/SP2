package be.ehb.spg3.entities;

import be.ehb.spg3.contracts.entities.IModelRepository;
import be.ehb.spg3.exceptions.ModelNotFoundException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

// Created by Wannes Gennar. All rights reserved

/**
 * The MySQLModelRepository is a base class for all model specific modelrepositories.
 * It provides generic handling of CRUD operations and some simple queries.
 *
 * @param <T> The model class to use.
 * @todo make MySQLModelRepository an abstract class.
 */
public class MySQLModelRepository<T> implements IModelRepository<T>
{
	private final Class<T> model;

	public MySQLModelRepository(Class<T> model)
	{
		this.model = model;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("be.ehb.spg3.persistence.HibernateUtil");
		factory.close();
	}

	/**
	 * Save a new model to the databases or update an existing one.
	 *
	 * @param obj The model instance to persist.
	 * @throws SQLException When an error occured.
	 */
	@Override
	public void save(T obj) throws SQLException
	{

	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 * @throws SQLException When an error occured.
	 */
	@Override
	public T find(long id) throws SQLException
	{
		return null;
	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 * @throws SQLException           When an SQL error occured.
	 */
	@Override
	public T findOrFail(long id) throws ModelNotFoundException, SQLException
	{
		return null;
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occured.
	 */
	@Override
	public List<T> findByField(String field, String value) throws SQLException
	{
		return null;
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occured.
	 */
	@Override
	public List<T> findByField(String field, int value) throws SQLException
	{
		return null;
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occured.
	 */
	@Override
	public List<T> findByField(String field, boolean value) throws SQLException
	{
		return null;
	}

	/**
	 * Get instances of T by searching given fields
	 *
	 * @param fields The fields to query
	 * @return A list of values matching given fields or null if an error was thrown
	 * @throws SQLException When an error occured.
	 */
	@Override
	public List<T> findByFields(String[]... fields) throws SQLException
	{
		return null;
	}

	/**
	 * Return a list with all models currently persisted in the databases.
	 *
	 * @return A list of persisted objects.
	 * @throws SQLException When an error occured.
	 * @apiNote Warning; this operation might take very long; avoid if possible!
	 */
	@Override
	public List<T> getAll() throws SQLException
	{
		return null;
	}

	/**
	 * Create the associated table if it does not exist yet.
	 *
	 * @throws SQLException When an error occured.
	 */
	@Override
	public void createIfNotExists() throws SQLException
	{

	}

	/**
	 * Create an instance of the model and set it's auto incrementing ID etc
	 *
	 * @return An instance of T
	 * @throws SQLException
	 */
	@Override
	public T create() throws SQLException
	{
		return null;
	}

	/**
	 * Remove a subject from the database.
	 *
	 * @param subject The subject to remove.
	 * @throws SQLException When an error occured.
	 */
	@Override
	public void delete(T subject) throws SQLException
	{

	}
}
