package be.ehb.spg3.controllers;

/**
 * Created by Jeroen_2 on 2/12/2015.
 */

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.engio.mbassy.listener.Handler;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;


public class AdminController implements Initializable
{

	@FXML
	public AnchorPane contentRoot;
	@FXML
	private Label lblUserName;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources)
	{
		this.lblUserName.setText("Jeroen");
		resolve(EventBus.class).subscribe(this); // register ourselves as an event listener
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("design/admin/editProfile.fxml"));
	}

	public void close()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchPaneEvent("design/admin/dashboard.fxml"));
//		Platform.exit();
	}

	@Handler
	public void changePanel(SwitchPaneEvent event)
	{
		Parent pane = null; // TODO: very dirty
		try
		{
			pane = FXMLLoader.load(event.getLocation());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		this.contentRoot.getChildren().clear();
		this.contentRoot.getChildren().add(pane);
	}
}