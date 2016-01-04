package be.ehb.spg3.controllers.user;

import be.ehb.spg3.entities.quizzes.Quiz;

public class CurrentQuiz
{
	private static Quiz quiz;

	public static void setQuiz(Quiz q)
	{
		quiz = q;
	}

	public static Quiz getQuiz()
	{
		return quiz;
	}
}
