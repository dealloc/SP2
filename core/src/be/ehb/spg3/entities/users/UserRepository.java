package be.ehb.spg3.entities.users;

import be.ehb.spg3.contracts.encryption.Hasher;
import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved
public class UserRepository extends MySQLModelRepository<User>
{
	public UserRepository() throws SQLException
	{
		super(User.class);
	}

	@Override
	public void save(User obj) throws SQLException
	{
		if (resolve(Hasher.class).needsRehash(obj.getPassword()))
		{
			obj.setPassword(resolve(Hasher.class).hash(obj.getPassword()));
		}

		super.save(obj);
	}
}
