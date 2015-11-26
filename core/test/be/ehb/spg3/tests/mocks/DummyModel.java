package be.ehb.spg3.tests.mocks;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

// Created by Wannes Gennar. All rights reserved
@DatabaseTable(tableName = "dummy")
public class DummyModel
{
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField private String name;
	@DatabaseField private int randomNumber;
	@DatabaseField private boolean testBool;
	@ForeignCollectionField
	ForeignCollection<DummyModel> models;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getRandomNumber()
	{
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber)
	{
		this.randomNumber = randomNumber;
	}

	public boolean isTestBool()
	{
		return testBool;
	}

	public void setTestBool(boolean testBool)
	{
		this.testBool = testBool;
	}

	public ForeignCollection getModels()
	{
		return this.models;
	}

	public void addModel(DummyModel model)
	{
		this.models.add(model);
	}
}
