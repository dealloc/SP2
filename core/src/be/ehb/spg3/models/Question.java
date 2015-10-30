package be.ehb.spg3.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

// Created by Wannes Gennar. All rights reserved

@DatabaseTable(tableName = "questions")
public class Question
{
	@DatabaseField(id = true)
	private int id;
	private List<Question> questions; // TODO a list of questions in a list; whut?
	private String question;
}
