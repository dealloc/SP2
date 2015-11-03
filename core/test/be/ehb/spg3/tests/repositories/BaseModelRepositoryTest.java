package be.ehb.spg3.tests.repositories;

import be.ehb.spg3.models.User;
import be.ehb.spg3.repositories.models.BaseModelRepository;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

// Created by Wannes Gennar. All rights reserved
public class BaseModelRepositoryTest extends DatabaseTestCase
{
	public BaseModelRepositoryTest() throws Exception
	{
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://dt5.ehb.be:3306/SP2_GR3", "SP2_GR3", "3qCxw");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// write DTD file
		FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("schema.dtd"));
	}

	/**
	 * Returns the test database connection.
	 */
	@Override
	protected IDatabaseConnection getConnection() throws Exception
	{
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://dt5.ehb.be:3306/SP2_GR3", "SP2_GR3", "3qCxw");

		return new DatabaseConnection(jdbcConnection);
	}

	/**
	 * Returns the test dataset.
	 */
	@Override
	protected IDataSet getDataSet() throws Exception
	{
		return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
	}

	@Test
	public void testFindById() throws Exception
	{
		BaseModelRepository<User> repository = new BaseModelRepository<>(User.class);
		User user = repository.find(1);
		assertNotNull(user);
		assertEquals(user.getId(), 1);
		assertEquals(user.getName(), "Wannes Gennar");
	}
}