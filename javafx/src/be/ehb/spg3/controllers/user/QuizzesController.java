package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class QuizzesController implements Initializable
{
	public static Quiz SELECTED_QUIZ = null;
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<Quiz> data = FXCollections.observableArrayList();
	@FXML
	private TableColumn tcQuiz;
	@FXML
	private TableView tvQuiz;
	@FXML
	private Button btnTake;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		//index.set(-1);
		resolve(EventBus.class).subscribe(this);
		try
		{
			List<Quiz> quizzes = resolve(QuizRepository.class).findByUser(resolve(Authenticator.class).auth());
			if (quizzes == null)
				return;

			for (Quiz q : quizzes)
			{
				if (data != null && q != null && q.getGroup().equals(resolve(Authenticator.class).auth().getGroup()))
				{
					data.add(q);
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcQuiz.setCellValueFactory(new PropertyValueFactory<Quiz, String>("name"));
		tvQuiz.setItems(data);

		tvQuiz.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
			index.set(data.indexOf(newValue));
			btnTake.setDisable(false);
		});
	}

	public void takeQuiz()
	{
		CurrentQuiz quiz = new CurrentQuiz();
		quiz.setQuiz(data.get(index.get()));
		QuizzesController.SELECTED_QUIZ = data.get(index.get());
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/user/takeQuiz.fxml", true));
	}
}