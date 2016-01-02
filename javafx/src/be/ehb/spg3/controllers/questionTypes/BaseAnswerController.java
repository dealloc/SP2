package be.ehb.spg3.controllers.questionTypes;

import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.questions.Question;

// Created by Wannes Gennar. All rights reserved
public abstract class BaseAnswerController
{
	protected Question question;

	public void setQuestion(Question question)
	{
		this.question = question;
		this.init();
	}

	public abstract Answer getAnswer();

	public abstract void init();
}
