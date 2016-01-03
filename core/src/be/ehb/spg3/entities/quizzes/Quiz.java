package be.ehb.spg3.entities.quizzes;

import be.ehb.spg3.entities.BaseEntity;
import be.ehb.spg3.entities.feedbacks.Feedback;
import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.questions.Question;
import be.ehb.spg3.entities.users.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

// Created by Wannes Gennar. All rights reserved.

/**
 * A quiz
 */
@Entity
@Table(name = "quizzes")
public class Quiz extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@OneToOne
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private User owner;
	@OneToOne
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Group group;
	@ManyToMany(targetEntity = Question.class)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Collection<Question> questions;
	@ManyToOne(targetEntity = Feedback.class)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Collection<Feedback> feedback;

	public Quiz()
	{
	}

	public Quiz(int id, String name, User owner, Group group, Collection<Question> questions, Collection<Feedback> feedback)
	{
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.group = group;
		this.questions = questions;
		this.feedback = feedback;
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

	public Group getGroup()
	{
		return group;
	}

	public void setGroup(Group group)
	{
		this.group = group;
	}
}
