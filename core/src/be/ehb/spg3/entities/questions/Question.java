package be.ehb.spg3.entities.questions;

import be.ehb.spg3.entities.BaseEntity;
import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.quizzes.Quiz;

import javax.persistence.*;
import java.util.Collection;

// Created by Wannes Gennar. All rights reserved

/**
 * A question of a Quiz
 */
@Entity
@Table(name = "questions")
public class Question extends BaseEntity
{
	@Column
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String question;
	@OneToOne
	private Quiz quiz;
	@Column
	private String mediaUrl;
	@Column
	@Enumerated(EnumType.STRING)
	private QuestionType type = QuestionType.MultipleChoice;
	@ManyToMany
	private Collection<Answer> answers;

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public QuestionType getType()
	{
		return type;
	}

	public void setType(QuestionType type)
	{
		this.type = type;
	}

	public Collection<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(Collection<Answer> answers)
	{
		this.answers = answers;
	}

	public void addAnswer(Answer answer)
	{
		this.answers.add(answer);
	}

	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}

	public String getMediaUrl()
	{
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl)
	{
		this.mediaUrl = mediaUrl;
	}
}
