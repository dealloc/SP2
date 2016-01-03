package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.results.Result;
import be.ehb.spg3.entities.results.ResultRepository;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class UserResultsController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<Result> data = FXCollections.observableArrayList();
	private java.util.List<Result> results;

	@FXML
	private Label lblQuizname;
	@FXML
	private TableView tvResult;
	@FXML
	private TableColumn tcUsername;
	@FXML
	private TableColumn tcFName;
	@FXML
	private TableColumn tcLName;
	@FXML
	private TableColumn tcScore;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			this.results = resolve(ResultRepository.class).getAll();
			/*this.results.parallelStream().forEach(result ->
			{
				Platform.runLater(() ->
				{
					if (result.getQuiz() != null && result.getUser() != null && result.getQuiz().equals(//Quiz die ge doorgeeft))
					{
						data.add(result);
					}
				});
			});*/
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcFName.setCellValueFactory(new PropertyValueFactory<Quiz, String>("name"));
		tcLName.setCellValueFactory(new PropertyValueFactory<Quiz, String>("surname"));
		tcUsername.setCellValueFactory(new PropertyValueFactory<Quiz, String>("username"));
		tcScore.setCellValueFactory(new PropertyValueFactory<Quiz, String>("score"));
		//TODO use correct property values (find a way to get User and Result)
		tvResult.setItems(data);
	}

	public void returnToQuizzes(){
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.manageQuizzes.fxml"));
	}
}
