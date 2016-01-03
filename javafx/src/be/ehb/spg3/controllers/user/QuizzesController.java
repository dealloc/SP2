package be.ehb.spg3.controllers.user;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.entities.quizzes.Quiz;
import be.ehb.spg3.entities.quizzes.QuizRepository;
import be.ehb.spg3.events.SwitchScreenEvent;
import be.ehb.spg3.events.TakeQuizControllerLoadedEvent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.engio.mbassy.listener.Handler;
import org.omg.CORBA.SetOverrideType;

import javax.swing.plaf.basic.BasicButtonUI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class QuizzesController implements Initializable
{
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
		resolve(EventBus.class).subscribe(this);
		try
		{
			data.addAll(resolve(QuizRepository.class).getAll()); //TODO only get quizzes from his group
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		tcQuiz.setCellValueFactory(new PropertyValueFactory<Quiz, String>("name"));
		tvQuiz.setItems(data);

		tvQuiz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
		{
			@Override
			public void changed(ObservableValue observable, Object oldvalue, Object newValue)
			{
				index.set(data.indexOf(newValue));
				btnTake.setDisable(false);
			}
		});
	}

	public void takeQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/user/questionType/imageQuestion.fxml", true));
	}

	@Handler
	public void takeQuizLoaded(TakeQuizControllerLoadedEvent event)
	{
		TakeQuizController.getInstance().setQuiz(data.get(index.get()));
	}
}
