package be.ehb.spg3.entities.questions;

import be.ehb.spg3.entities.BaseModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class QuestionRepository extends BaseModelRepository<Question>
{
	public QuestionRepository() throws SQLException
	{
		super(Question.class);
	}
}
