package be.ehb.spg3.entities;

import be.ehb.spg3.contracts.entities.IModelRepository;
import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.exceptions.ModelNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved

/**
 * The MySQLModelRepository is a base class for all model specific modelrepositories.
 * It provides generic handling of CRUD operations and some simple queries.
 *
 * @param <T> The model class to use.
 */
public abstract class MySQLModelRepository<T extends BaseEntity> implements IModelRepository<T>
{
	protected final Class<T> model;
	protected final EntityManager manager;

	public MySQLModelRepository(Class<T> model)
	{
		this.model = model;
		this.manager = resolve(IDatabaseRepository.class).getManager();
	}

	private void begin()
	{
		if (!this.manager.getTransaction().isActive())
		{
			this.manager.getTransaction().begin();
		}
	}

	private void finish()
	{
		this.manager.getTransaction().commit();
	}

	/**
	 * Save a new model to the databases or update an existing one.
	 *
	 * @param obj The model instance to persist.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public void save(T obj) throws SQLException
	{
		this.begin();

		this.manager.persist(obj);

		this.finish();
	}

	/**
	 * Update an existing model in the database.
	 *
	 * @param obj The model to update.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public void update(T obj) throws SQLException
	{
		this.begin();

		this.manager.merge(obj);

		this.finish();
	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID, or null if none was found.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public T find(long id) throws SQLException
	{
		return this.manager.find(this.model, id);
	}

	/**
	 * Get an instance of T by it's ID.
	 *
	 * @param id The model ID.
	 * @return An instance of T matching the given ID.
	 * @throws ModelNotFoundException Thrown when no model with given ID was found.
	 * @throws SQLException           When an SQL error occurred.
	 */
	@Override
	public T findOrFail(long id) throws ModelNotFoundException, SQLException
	{
		T result = this.find(id);
		if (result == null)
		{
			throw new ModelNotFoundException("Could not find " + this.model.getName() + " with id " + id);
		}

		return result;
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public List<T> findByField(String field, String value) throws SQLException
	{
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.model);

		Root<T> root = criteria.from(this.model);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(field), value));

		TypedQuery<T> query = this.manager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public List<T> findByField(String field, int value) throws SQLException
	{
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.model);

		Root<T> root = criteria.from(this.model);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(field), value));

		TypedQuery<T> query = this.manager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public List<T> findByField(String field, long value) throws SQLException
	{
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.model);

		Root<T> root = criteria.from(this.model);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(field), value));

		TypedQuery<T> query = this.manager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * Get instances of T by searching a given field.
	 *
	 * @param field The field to query.
	 * @param value The value the given field should match.
	 * @return A list of instances of T matching given query, or an empty list if none were found.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public List<T> findByField(String field, boolean value) throws SQLException
	{
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.model);

		Root<T> root = criteria.from(this.model);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(field), value));

		TypedQuery<T> query = this.manager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * Get instances of T by searching given fields
	 *
	 * @param fields The fields to query
	 * @return A list of values matching given fields or null if an error was thrown
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public List<T> findByFields(String[]... fields) throws SQLException
	{
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.model);

		Root<T> root = criteria.from(this.model);
		criteria.select(root);
		for (String[] field : fields)
			criteria.where(criteriaBuilder.equal(root.get(field[0]), field[1]));

		TypedQuery<T> query = this.manager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * Return a list with all models currently persisted in the databases.
	 * <br>
	 * <b>Warning; this operation might take very long; avoid if possible!</b>
	 *
	 * @return A list of persisted objects.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public List<T> getAll() throws SQLException
	{
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.model);

		Root<T> root = criteria.from(this.model);
		criteria.select(root);

		TypedQuery<T> query = this.manager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * Create the associated table if it does not exist yet.
	 *
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public void createIfNotExists() throws SQLException
	{
		// NOTE hibernate does this automaticly on startup for us
	}

	/**
	 * Check if the object exists in the database.
	 *
	 * @param subject The object to check.
	 * @return true if the object exists in the database, false if it doesn't.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public boolean exists(T subject) throws SQLException
	{
		final CriteriaBuilder cb = this.manager.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<T> from = cq.from(this.model);

		cq.select(cb.count(from));
		cq.where(cb.equal(from.get("id"), subject.getId()));

		final TypedQuery<Long> tq = this.manager.createQuery(cq);
		return tq.getSingleResult() > 0;
	}

	/**
	 * Create an instance of the model and set it's auto incrementing ID etc
	 *
	 * @return An instance of T
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public T create() throws SQLException
	{
		return resolve(this.model); // let the IoC do the hard work on this one
	}

	/**
	 * Remove a subject from the database.
	 *
	 * @param subject The subject to remove.
	 * @throws SQLException When an error occurred.
	 */
	@Override
	public void delete(T subject) throws SQLException
	{
		this.begin();

		this.manager.remove(subject);

		this.finish();
	}
}
