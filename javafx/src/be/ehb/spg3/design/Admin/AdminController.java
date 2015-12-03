package be.ehb.spg3.design.Admin;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML //  fx:id="myButton"
    private Button myButton; // Value injected by FXMLLoader
    @FXML
    private Label lblUserName;
    @FXML
    private AnchorPane apRoot;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'AdminHome.fxml'.";
        lblUserName.setText("Jeroen");
    }

    public void test() {
        myButton.setText("You clicked me!");
        myButton.setPrefWidth(250);
    }
}
