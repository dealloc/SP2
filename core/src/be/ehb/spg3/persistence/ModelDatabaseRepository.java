package be.ehb.spg3.persistence;

import be.ehb.spg3.contracts.persistence.IDatabaseRepository;
import be.ehb.spg3.entities.feedbacks.Feedback;
import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.permissions.Permission;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.results.Result;
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

		prop.setProperty("hibernate.c3p0.min_size", "5"); // Minimum number of JDBC connections in the pool. Hibernate default: 1
		prop.setProperty("hibernate.c3p0.max_size", "20"); // Maximum number of JDBC connections in the pool. Hibernate default: 100
		prop.setProperty("hibernate.c3p0.timeout", "300"); // When an idle connection is removed from the pool (in second). Hibernate default: 0, never expire.
		prop.setProperty("hibernate.c3p0.max_statements", "50"); // Number of prepared statements will be cached. Increase performance. Hibernate default: 0 , caching is disable.
		prop.setProperty("hibernate.c3p0.idle_test_period", "3000"); // idle time in seconds before a connection is automatically validated. Hibernate default: 0

		this.factory = new Configuration()
				               .addPackage("be.ehb.spg3.entities")
				               .addProperties(prop)
				               .addAnnotatedClass(Feedback.class)
				               .addAnnotatedClass(Group.class)
				               .addAnnotatedClass(Permission.class)
				               .addAnnotatedClass(Question.class)
				               .addAnnotatedClass(Quiz.class)
				               .addAnnotatedClass(Result.class)
				               .addAnnotatedClass(Role.class)
				               .addAnnotatedClass(User.class)
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
