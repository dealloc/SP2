package be.ehb.spg3.entities.questions;

import be.ehb.spg3.entities.MySQLModelRepository;
import be.ehb.spg3.entities.quizzes.Quiz;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

// Created by Wannes Gennar. All rights reserved
public class QuestionRepository extends MySQLModelRepository<Question>
{
	public QuestionRepository() throws SQLException
	{
		super(Question.class);
	}

	public List<Question> findByQuiz(Quiz quiz)
	{
		Query query = this.manager.createQuery("FROM Question WHERE quiz = :quizid");
		query.setParameter("quizid", quiz);

		return (List<Question>) query.getResultList();
	}
}
