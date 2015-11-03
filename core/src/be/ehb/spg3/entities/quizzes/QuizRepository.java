package be.ehb.spg3.entities.quizzes;

import be.ehb.spg3.entities.BaseModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class QuizRepository extends BaseModelRepository<Quiz>
{
	public QuizRepository() throws SQLException
	{
		super(Quiz.class);
	}
}
