package be.ehb.spg3.entities.results;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class ResultRepository extends MySQLModelRepository<Result>
{
	public ResultRepository() throws SQLException
	{
		super(Result.class);
	}
}
