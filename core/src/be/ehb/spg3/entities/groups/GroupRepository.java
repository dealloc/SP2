package be.ehb.spg3.entities.groups;

import be.ehb.spg3.entities.BaseModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class GroupRepository extends BaseModelRepository<Group>
{
	public GroupRepository() throws SQLException
	{
		super(Group.class);
	}
}
