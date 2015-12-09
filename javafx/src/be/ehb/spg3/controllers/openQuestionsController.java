package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Anthony Min. All rights reserved
public class openQuestionsController implements Initializable
{
	@FXML
	private Button btnStart;
	@FXML
	private Button btnSluitVragen;
	@FXML
	private TableColumn naam;
	@FXML
	private TableColumn publisher;
	@FXML
	private TableColumn amountQ;



	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	public void openClosedQuestions()
	{
		resolve(EventBus.class).fire(new SwitchScreenEvent("design/user/profile_fxml/userPaneClosedQuestions.fxml", false));
	}

	public void startQuiz()
	{
		btnStart.setText("dit is de open controller");
		name.setText("lol");
	}
}