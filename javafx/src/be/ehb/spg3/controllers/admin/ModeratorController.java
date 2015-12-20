package be.ehb.spg3.controllers.admin;
// Created by Simon Poll� All rights reserved

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

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

	public void clickMe(){
		test.setText("test");
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
