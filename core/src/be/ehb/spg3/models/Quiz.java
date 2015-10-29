package be.ehb.spg3.models;

import java.util.List;

// Created by Wannes Gennar. All rights reserved
public class Quiz
{
	private int id;
	private String name;
	private User owner;
	private List<User> users;
	private List<Question> questions;
	private List<Feedback> feedback;

	public Quiz()
	{
	}

	public Quiz(int id, String name, User owner, List<User> users, List<Question> questions, List<Feedback> feedback)
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

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}

	public List<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(List<Question> questions)
	{
		this.questions = questions;
	}

	public List<Feedback> getFeedback()
	{
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback)
	{
		this.feedback = feedback;
	}
}
