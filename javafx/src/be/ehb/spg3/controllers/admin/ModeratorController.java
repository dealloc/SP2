package be.ehb.spg3.controllers.admin;
// Created by Simon Poll� All rights reserved

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.SwitchScreenEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class ModeratorController implements Initializable
{
	@FXML
	private Button test;

	@FXML
	private Button dashboard;

	@FXML
	private Button users;

	@FXML
	private Button statistics;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public void addQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/moderator/quizTool_addQuiz.fxml", false));
	}

	public void editQuiz()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/moderator/quizTool_editQuiz.fxml", false));
	}

	public void deleteQuiz()
	{

	}

	public void assignUsers()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/moderator/quizTool_assignUsers.fxml", false));
	}

	public void disableQuiz()
	{

	}

	public void enableQuiz()
	{

	}

	public void showResults()
	{
		resolve(EventBus.class).fireSynchronous(new SwitchScreenEvent("design/moderator/quizTool_results.fxml", false));
	}

	public void importQuiz()
	{

	}

	public void exportQuiz()
	{

	}

	public void startDashboard()
	{

	}

	public void startUsers()
	{

	}

	public void startStatistics()
	{

	}
}
