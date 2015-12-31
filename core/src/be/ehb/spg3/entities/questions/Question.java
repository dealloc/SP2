package be.ehb.spg3.entities.questions;

import be.ehb.spg3.entities.BaseEntity;

import javax.persistence.*;

// Created by Wannes Gennar. All rights reserved

/**
 * A question of a Quiz
 */
@Entity
@Table(name = "questions")
public class Question extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String question;

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}
}
