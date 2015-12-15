package be.ehb.spg3.tests.mocks;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class DummyRepository extends MySQLModelRepository<DummyModel>
{
	public DummyRepository() throws SQLException
	{
		super(DummyModel.class);
	}
}
