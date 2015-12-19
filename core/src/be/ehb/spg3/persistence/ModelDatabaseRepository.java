package be.ehb.spg3.persistence;

import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.users.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

// Created by Wannes Gennar. All rights reserved
public class ModelDatabaseRepository implements IDatabaseRepository
{
	SessionFactory factory;

	@Override
	public void initialize()
	{
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:mysql://dt5.ehb.be:3306/SP2_GR3");
		prop.setProperty("hibernate.connection.username", "SP2_GR3");
		prop.setProperty("hibernate.connection.password", "3qCxw");
		prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.id.new_generator_mappings", "false");
		prop.setProperty("hibernate.hbm2ddl.auto", "create");

		this.factory = new Configuration()
				               .addPackage("be.ehb.spg3.entities")
				               .addProperties(prop)
				               .addAnnotatedClass(User.class)
				               .addAnnotatedClass(Group.class)
				               .addAnnotatedClass(Role.class)
				               .addAnnotatedClass(Permission.class)
				               .buildSessionFactory();
	}

	@Override
	public void finish()
	{
		this.factory.close();
	}

	@Override
	public <T> void createOrUpdate(T model)
	{
		this.factory.getCurrentSession().saveOrUpdate(model);
	}
}
