package be.ehb.spg3.entities.users;

import be.ehb.spg3.entities.BaseModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class UserRepository extends BaseModelRepository<User>
{
	public UserRepository() throws SQLException
	{
		super(User.class);
	}
}
