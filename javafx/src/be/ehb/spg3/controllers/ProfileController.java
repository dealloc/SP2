package be.ehb.spg3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

// Created by Anthony Min. All rights reserved
public class ProfileController implements Initializable
{
	@FXML
	private Button btnStart;


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public void openClosedQuestions()
	{

	}

	public void startQuiz()
	{
		btnStart.setText("lol");
	}
}
