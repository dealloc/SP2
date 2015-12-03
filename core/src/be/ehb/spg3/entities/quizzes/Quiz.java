package be.ehb.spg3.entities.quizzes;

import be.ehb.spg3.entities.feedbacks.Feedback;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.users.User;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

// Created by Wannes Gennar. All rights reserved.

/**
 * TODO change to javax.sql annotations to remove coupling!
 */
@DatabaseTable(tableName = "quizzes")
public class Quiz
{
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private User owner;
	@ForeignCollectionField
	private ForeignCollection<User> users;
	@ForeignCollectionField
	private ForeignCollection<Question> questions;
	@ForeignCollectionField
	private ForeignCollection<Feedback> feedback;

	public Quiz()
	{
	}

	public Quiz(int id, String name, User owner, ForeignCollection<User> users, ForeignCollection<Question> questions, ForeignCollection<Feedback> feedback)
	{
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.users = users;
		this.questions = questions;
		this.feedback = feedback;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public User getOwner()
	{
		return owner;
	}

	public void setOwner(User owner)
	{
		this.owner = owner;
	}

	public ForeignCollection<User> getUsers()
	{
		return users;
	}

	public void setUsers(ForeignCollection<User> users)
	{
		this.users = users;
	}

	public ForeignCollection<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(ForeignCollection<Question> questions)
	{
		this.questions = questions;
	}

	public ForeignCollection<Feedback> getFeedback()
	{
		return feedback;
	}

	public void setFeedback(ForeignCollection<Feedback> feedback)
	{
		this.feedback = feedback;
	}
}
