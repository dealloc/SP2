package be.ehb.spg3.controllers.questionTypes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ImageQuestionController implements Initializable
{
	@FXML
	private Label lblQuestion;
	@FXML
	private FlowPane answerPane;
	@FXML
	private ImageView imgView;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//TODO update "lblQuestion" and add the needed media to "mediaPane"
		Image img = new Image("http://www.addletters.com/pictures/bart-simpson-generator/bart-simpson-generator.php?line=I+will+tell+all+of+my+friends+about+addletters.com+today!");
		imgView.setImage(img);
	}
}
