package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.answer.Answer;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.entities.results.Result;
import be.ehb.spg3.entities.results.ResultRepository;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class ResultController implements Initializable
{
	private ObservableList<String> scores = FXCollections.observableArrayList();

	@FXML
	private TableView tvResult;
	@FXML
	private TableColumn tcQuiz;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			List<Quiz> quizzes = resolve(QuizRepository.class).findByUser(resolve(Authenticator.class).auth());
			quizzes.parallelStream()
					.filter(q -> q.getGroup().getId() == resolve(Authenticator.class).auth().getGroup().getId())
					.forEach(quiz ->
					{
						Result result = resolve(ResultRepository.class).getResult(quiz, resolve(Authenticator.class).auth());
						if (result != null)
						{
							long score = result.getAnswers().parallelStream().filter(Answer::isCorrect).count();
							long total = result.getAnswers().size();
							scores.add(quiz.getName() + ": " + score + "/" + total);
						}
						else
						{
							scores.add(quiz.getName() + ": nog niet afgelegd");
						}
					});
		}
		catch (SQLException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
		}

		this.tcQuiz.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>()
		{
			@Override
			public ObservableValue call(TableColumn.CellDataFeatures param)
			{
				return new ReadOnlyStringWrapper((String) param.getValue());
			}
		});
		tvResult.setItems(scores);
	}
}
