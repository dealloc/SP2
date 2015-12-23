package be.ehb.spg3.persistence;

import be.ehb.spg3.contracts.persistence.IDatabaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Created by Wannes Gennar. All rights reserved

/**
 * Implementation of the IDatabaseRepository contract for Hibernate
 */
public class ModelDatabaseRepository implements IDatabaseRepository
{
	EntityManager manager;

	/**
	 * Initialize the database, it's connections and setup the environment to communicate.
	 */
	@Override
	public void initialize()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PRunit");
		this.manager = entityManagerFactory.createEntityManager();
	}

	/**
	 * Clean up all used resources and connections with the database.
	 */
	@Override
	public void finish()
	{
		this.manager.close();
	}

	@Override
	public EntityManager getManager()
	{
		return this.manager;
	}
}
