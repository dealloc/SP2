package be.ehb.spg3.entities.results;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.entities.BaseEntity;
import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.users.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

/**
 * The result of a user finishing a quiz.
 */
@Entity
@Table(name = "results")
public class Result extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private Quiz quiz;
	@OneToOne
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private User user;
	@ManyToMany
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private Collection<Answer> answers;

	public Result()
	{
	}

	public Result(Quiz quiz, User user)
	{
		this.quiz = quiz;
		this.user = user;
	}

	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Collection<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(Collection<Answer> answers)
	{
		this.answers = answers;
	}
}
