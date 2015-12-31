package be.ehb.spg3.persistence;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.entities.BaseEntity;
import be.ehb.spg3.entities.MySQLModelRepository;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.permissions.PermissionRepository;
import be.ehb.spg3.entities.users.User;
import be.ehb.spg3.entities.users.UserRepository;
import be.ehb.spg3.events.errors.ErrorEvent;
import com.mchange.v2.log.MLevel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

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

		// initialize permissions
		PermissionRepository repository = resolve(PermissionRepository.class);
		this.initModel(repository, Permission.MANAGE_USERS);
		this.initModel(repository, Permission.MANAGE_ROLES);

		// TODO initialize roles

		// initialize users
		this.initModel(resolve(UserRepository.class), User.GUEST);
	}

	private <T extends BaseEntity> void initModel(MySQLModelRepository<T> repository, T model)
	{
		try
		{
			repository.update(model);
		}
		catch (SQLException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}
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
