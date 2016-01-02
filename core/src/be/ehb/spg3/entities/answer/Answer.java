package be.ehb.spg3.entities.answer;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String text;
	@Column
	private boolean correct;
	
	public Answer(String text, boolean selected)
	{

	}
	
	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public boolean isCorrect()
	{
		return correct;
	}

	public void setCorrect(boolean correct)
	{
		this.correct = correct;
	}
}
