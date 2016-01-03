package be.ehb.spg3.entities.quizzes;

import be.ehb.spg3.entities.MySQLModelRepository;
import be.ehb.spg3.entities.users.User;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

// Created by Wannes Gennar. All rights reserved
public class QuizRepository extends MySQLModelRepository<Quiz>
{
	public QuizRepository() throws SQLException
	{
		super(Quiz.class);
	}

	public List<Quiz> findByUser(User user) throws SQLException
	{
		Query query = this.manager.createQuery("from Quiz where group = :usergroup");
		query.setParameter("usergroup", user.getGroup());

		return query.getResultList();
	}
}
