package be.ehb.spg3.controllers.moderator;

import be.ehb.spg3.contracts.auth.Authenticator;
import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class ManageQuizzesController implements Initializable
{
	private IntegerProperty index = new SimpleIntegerProperty();
	private ObservableList<Quiz> data = FXCollections.observableArrayList();
	private List<Quiz> quizzes;

	@FXML
	private TableView tvQuizzes;
	@FXML
	private TableColumn tcQuizzes;
	@FXML
	private FlowPane flowSure;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			this.quizzes = resolve(QuizRepository.class).getAll();
			this.quizzes.parallelStream().forEach(quiz ->
			{
				Platform.runLater(() ->
				{
					if (quiz.getOwner() != null && quiz.getOwner().equals(resolve(Authenticator.class).auth()))
					{
						data.add(quiz);
					}
				});
			});
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcQuizzes.setCellValueFactory(new PropertyValueFactory<Quiz, String>("name"));
		tvQuizzes.setItems(data);
		tvQuizzes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				index.set(data.indexOf(newValue));
				flowSure.setVisible(false);
			}
		});
	}

	public void viewResults(){
		//TODO handler om quiz door te sturen naar userResultConntroller
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("moderator.userResults.fxml"));
	}

	public void deleteQuiz()
	{
		flowSure.setVisible(true);
	}

	public void yes()
	{
		try
		{
			resolve(QuizRepository.class).delete(data.get(index.get()));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		data.remove(index.get());
		tvQuizzes.getSelectionModel().clearSelection();
		Notifications.create().text("Quiz removed").darkStyle().showConfirm();
		flowSure.setVisible(false);
	}

	public void sortQuiz()
	{
		flowSure.setVisible(false);
	}
}
