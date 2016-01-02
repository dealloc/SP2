package be.ehb.spg3.events;

import be.ehb.spg3.entities.questions.Question;

// Created by Wannes Gennar. All rights reserved
public class QuestionAddedEvent
{
	private final Question question;

	public QuestionAddedEvent(Question question)
	{
		this.question = question;
	}

	public Question getQuestion()
	{
		return question;
	}
}
