package be.ehb.spg3.entities.feedbacks;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.BaseEntity;
import be.ehb.spg3.entities.users.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Feedback given by the user.
 */
@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String feedback;
	@OneToOne
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
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
