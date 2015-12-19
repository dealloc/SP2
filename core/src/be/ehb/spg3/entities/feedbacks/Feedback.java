package be.ehb.spg3.entities.feedbacks;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.users.User;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback
{
	@Column
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String feedback;
	@Column
	@ManyToOne
	private User user;

	public Feedback()
	{
	}

	public Feedback(String feedback, User user)
	{
		this.feedback = feedback;
		this.user = user;
	}

	public String getFeedback()
	{
		return feedback;
	}

	public void setFeedback(String feedback)
	{
		this.feedback = feedback;
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
