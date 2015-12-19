package be.ehb.spg3.persistence;

import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.roles.Role;
import be.ehb.spg3.entities.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

// Created by Wannes Gennar. All rights reserved
public class HibernateUtil
{
	public static void main()
	{
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:mysql://dt5.ehb.be:3306/SP2_GR3");
		prop.setProperty("hibernate.connection.username", "SP2_GR3");
		prop.setProperty("hibernate.connection.password", "3qCxw");
		prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.id.new_generator_mappings", "false");
		prop.setProperty("hibernate.hbm2ddl.auto", "create");

		SessionFactory factory = new Configuration()
				                         .addPackage("be.ehb.spg3.entities")
				                         .addProperties(prop)
				                         .addAnnotatedClass(User.class)
				                         .addAnnotatedClass(Group.class)
				                         .addAnnotatedClass(Role.class)
				                         .addAnnotatedClass(Permission.class)
				                         .buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();
		session.persist(new User("wannes", "gennar", "wannes.gennar@gmail.com", "dealloc", "password"));
		session.getTransaction().commit();
		session.close();
	}
}
