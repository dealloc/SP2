package be.ehb.spg3.entities.quizzes;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class QuizRepository extends MySQLModelRepository<Quiz>
{
	public QuizRepository() throws SQLException
	{
		super(Quiz.class);
	}
}
