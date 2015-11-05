package be.ehb.spg3.tests.mocks;

import be.ehb.spg3.entities.BaseModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class DummyRepository extends BaseModelRepository<DummyModel>
{
	public DummyRepository() throws SQLException
	{
		super(DummyModel.class);
	}
}
