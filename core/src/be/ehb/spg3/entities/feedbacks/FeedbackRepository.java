package be.ehb.spg3.entities.feedbacks;

import be.ehb.spg3.entities.MySQLModelRepository;

import java.sql.SQLException;

// Created by Wannes Gennar. All rights reserved
public class FeedbackRepository extends MySQLModelRepository<Feedback>
{
	public FeedbackRepository() throws SQLException
	{
		super(Feedback.class);
	}
}
