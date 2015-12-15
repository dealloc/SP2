package be.ehb.spg3.entities.roles;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class RoleRepository extends MySQLModelRepository<Role>
{
	public RoleRepository() throws SQLException
	{
		super(Role.class);
	}
}
