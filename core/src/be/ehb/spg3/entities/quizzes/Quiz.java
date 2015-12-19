package be.ehb.spg3.entities.quizzes;

import be.ehb.spg3.entities.feedbacks.Feedback;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.users.User;

import javax.persistence.*;
import java.util.Collection;

// Created by Wannes Gennar. All rights reserved.

@Entity
@Table(name = "quizzes")
public class Quiz
{
	@Column
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private User owner;
	@Column
	@ManyToMany
	private Collection<User> users;
	@Column
	@ManyToMany
	private Collection<Question> questions;
	@Column
	@ManyToOne
	private Collection<Feedback> feedback;

	public Quiz()
	{
	}

	public Quiz(int id, String name, User owner, Collection<User> users, Collection<Question> questions, Collection<Feedback> feedback)
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

	public Collection<User> getUsers()
	{
		return users;
	}

	public void setUsers(Collection<User> users)
	{
		this.users = users;
	}

	public Collection<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(Collection<Question> questions)
	{
		this.questions = questions;
	}

	public Collection<Feedback> getFeedback()
	{
		return feedback;
	}

	public void setFeedback(Collection<Feedback> feedback)
	{
		this.feedback = feedback;
	}
}
