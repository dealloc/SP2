package be.ehb.spg3.controllers.user;

import be.ehb.spg3.entities.groups.Group;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class ResultController implements Initializable
{
	private ObservableList<Quiz> data = FXCollections.observableArrayList();

	@FXML
	private TableView tvResult;
	@FXML
	private TableColumn tcQuiz;
	@FXML
	private TableColumn tcScore;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			data.addAll(resolve(QuizRepository.class).getAll());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcQuiz.setCellValueFactory(new PropertyValueFactory<Quiz, String>("name"));
		tcScore.setCellValueFactory(new PropertyValueFactory<Quiz, String>("score"));
		tvResult.setItems(data);

		//TODO load quizzes with the user his matching score
		//TODO add integer score in Result class?
	}
}
