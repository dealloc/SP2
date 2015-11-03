package be.ehb.spg3.tests.repositories;

import be.ehb.spg3.entities.BaseModelRepository;
import be.ehb.spg3.exceptions.ModelNotFoundException;
import be.ehb.spg3.tests.mocks.DummyModel;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

// Created by Wannes Gennar. All rights reserved
@RunWith(JUnit4.class)
public class BaseModelRepositoryTest extends DatabaseTestCase
{
	BaseModelRepository<DummyModel> repository = new BaseModelRepository<>(DummyModel.class);

	public BaseModelRepositoryTest() throws Exception
	{
		IDatabaseConnection connection = this.getConnection();

		// write DTD file
		FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("schema.dtd"));
	}

	@Before
	public void setUp() throws Exception
	{
		ConnectionSource connection = new JdbcConnectionSource("jdbc:mysql://dt5.ehb.be:3306/SP2_GR3", "SP2_GR3", "3qCxw");
		TableUtils.dropTable(connection, DummyModel.class, true);
		TableUtils.createTableIfNotExists(connection, DummyModel.class);
		TableUtils.clearTable(connection, DummyModel.class);
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
		return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("dataset.xml"));
	}

	private void initializeOneModel() throws Exception
	{
		DummyModel model = new DummyModel();
		model.setName("Hello world");
		model.setRandomNumber(420);
		model.setTestBool(true);
		this.repository.save(model);
	}

	@Test
	public void testSave() throws Exception
	{
		DummyModel model = new DummyModel();
		model.setName("Hello world");
		this.repository.save(model);
		List<DummyModel> check = this.repository.findByField("name", "Hello world");
		assertEquals(1, check.size());
		assertNotNull(check.get(0));
		assertEquals("Hello world", check.get(0).getName());
		assertEquals(1, check.get(0).getId());
	}

	@Test
	public void testFindById() throws Exception
	{
		this.initializeOneModel();
		DummyModel model = this.repository.find(1);
		assertNotNull(model);
		assertEquals(1, model.getId());
		assertEquals("Hello world", model.getName());
	}

	@Test(expected = ModelNotFoundException.class)
	public void testFindOrFail() throws Exception
	{
		this.repository.findOrFail(-1);
		fail("Find or fail should have thrown");
	}
	
	@Test
	public void testFindByFieldString() throws Exception
	{
		this.initializeOneModel();
		this.initializeOneModel();

		List<DummyModel> list = this.repository.findByField("name", "Hello world");
		assertEquals(2, list.size());
		assertNotNull(list.get(0));
		assertNotNull(list.get(1));
		assertEquals("Hello world", list.get(0).getName());
		assertEquals("Hello world", list.get(1).getName());
	}

	@Test
	public void testFindFieldByInteger() throws Exception
	{
		this.initializeOneModel();
		this.initializeOneModel();

		List<DummyModel> list = this.repository.findByField("randomNumber", 420);
		assertEquals(2, list.size());
		assertNotNull(list.get(0));
		assertNotNull(list.get(1));
		assertEquals(420, list.get(0).getRandomNumber());
		assertEquals(420, list.get(1).getRandomNumber());
	}

	@Test
	public void testFindFieldByBoolean() throws Exception
	{
		this.initializeOneModel();
		this.initializeOneModel();

		List<DummyModel> list = this.repository.findByField("testBool", true);
		assertEquals(2, list.size());
		assertNotNull(list.get(0));
		assertNotNull(list.get(1));
		assertEquals(true, list.get(0).isTestBool());
		assertEquals(true, list.get(1).isTestBool());
	}
}