package be.ehb.spg3.entities.permissions;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class PermissionRepository extends MySQLModelRepository<Permission>
{
	public PermissionRepository() throws SQLException
	{
		super(Permission.class);
	}
}
