package be.ehb.spg3.models;

// Created by Wannes Gennar. All rights reserved

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "results")
public class Result
{
	@DatabaseField(id = true)
	private int id;
	private Quiz quiz;
	private User user;

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
