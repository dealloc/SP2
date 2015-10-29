package be.ehb.spg3.models;

// Created by Wannes Gennar. All rights reserved
public class Feedback
{
	private String feedback;
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
