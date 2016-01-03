package be.ehb.spg3.entities.results;

import be.ehb.spg3.entities.MySQLModelRepository;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.users.User;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

// Created by Wannes Gennar. All rights reserved
public class ResultRepository extends MySQLModelRepository<Result>
{
	public ResultRepository() throws SQLException
	{
		super(Result.class);
	}

	public Result getResult(Quiz quiz, User user)
	{
		Query query = this.manager.createQuery("from Result where quiz = :quiz and user = :user");
		query.setParameter("quiz", quiz);
		query.setParameter("user", user);

		List<Result> results = query.getResultList();
		if (results.size() == 0)
		{
			return null;
		}
		else
		{
			return results.get(0);
		}
	}
}
