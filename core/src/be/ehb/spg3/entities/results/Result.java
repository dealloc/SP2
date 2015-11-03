package be.ehb.spg3.entities.results;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.users.User;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * TODO change to javax.sql annotations to remove coupling!
 */
@DatabaseTable(tableName = "results")
public class Result
{
	@DatabaseField(id = true) private int id;
	@DatabaseField private Quiz quiz;
	@DatabaseField private User user;

	public Result()
	{
	}

	public Result(Quiz quiz, User user)
	{
		this.quiz = quiz;
		this.user = user;
	}

	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
