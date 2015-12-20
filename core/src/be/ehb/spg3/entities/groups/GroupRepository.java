package be.ehb.spg3.entities.groups;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class GroupRepository extends MySQLModelRepository<Group>
{
	public GroupRepository() throws SQLException
	{
		super(Group.class);
	}
}
