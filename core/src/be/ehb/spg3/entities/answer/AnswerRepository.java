package be.ehb.spg3.entities.answer;

import be.ehb.spg3.entities.MySQLModelRepository;

// Created by Wannes Gennar. All rights reserved
public class AnswerRepository extends MySQLModelRepository<Answer>
{
	public AnswerRepository()
	{
		super(Answer.class);
	}
}
