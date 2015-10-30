package be.ehb.spg3.models;

// Created by Wannes Gennar. All rights reserved

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "feedbacks")
public class Feedback
{
	@DatabaseField(id = true)
	private int id;
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
