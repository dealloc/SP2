package be.ehb.spg3.entities.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Created by Wannes Gennar. All rights reserved

@Entity
public class Question
{
	@Column
	@Id
	@GeneratedValue
	private int id;
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
