package be.ehb.spg3.persistence;

import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import com.mchange.v2.log.MLevel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); // set hibernate logging
		com.mchange.v2.log.MLog.getLogger().setLevel(MLevel.SEVERE);

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
