package be.ehb.spg3.entities.results;

import be.ehb.spg3.entities.BaseModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class ResultRepository extends BaseModelRepository<Result>
{
	public ResultRepository() throws SQLException
	{
		super(Result.class);
	}
}
