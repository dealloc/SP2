package be.ehb.spg3.controllers;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchPaneEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Anthony Min. All rights reserved
public class ClosedQuestionsController implements Initializable
{
	@FXML
	private Button btnStart;
	@FXML
	private TableColumn naam;
	@FXML
	private TableColumn publisher;
	@FXML
	private TableColumn quizDone;


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public void openOpenQuestions()
	{
		resolve(EventBus.class).fire(new SwitchPaneEvent("design/user/profile_fxml/userPaneClosedQuestions.fxml"));
	}

	public void startQuiz()
	{
		btnStart.setText("dit is de closed controller");
	}
}