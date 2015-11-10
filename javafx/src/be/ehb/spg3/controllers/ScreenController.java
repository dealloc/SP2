package be.ehb.spg3.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

// Created by Wannes Gennar. All rights reserved

/**
 * Manages switching between screens, takes care of transitions and lifecycles.
 */
public class ScreenController
{
	private StackPane stack;

	/**
	 * Attach this screencontroller to a Stage.
	 *
	 * @param stage The stage to attach this controller to
	 * @return The previous Scene of the stage (or null if none was set)
	 */
	public Scene attachTo(Stage stage)
	{
		Scene old = stage.getScene();
		stage.setScene(new Scene(this.stack));
		return old;
	}

	/**
	 * TODO write documentation for this method
	 * @param res
	 * @param anim
	 * @throws IOException
	 * @todo implement controller lifecycle callbacks.
	 */
	public void show(URL res, TransitionAnimation anim) throws IOException
	{
		Node current = this.stack.getChildren().get(0);
		Parent next = FXMLLoader.load(res);
		this.stack.getChildren().add(next);
		anim.transition(current, next);
		this.stack.getChildren().remove(0);
	}
}
