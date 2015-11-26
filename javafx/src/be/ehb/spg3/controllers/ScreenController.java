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
	private FXMLLoader fxmlLoader;

	public ScreenController()
	{
		this.stack = new StackPane();
		this.fxmlLoader = new FXMLLoader();
	}

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
	 * @param res The URL to the FXML file that should be loaded.
	 * @param anim The animation that should be used to transit between the two screens.
	 * @throws IOException Thrown when the FXML file couldn't be loaded
	 */
	public void show(URL res, TransitionAnimation anim) throws IOException
	{
		this.dispatchDetachEvent();
		Node current = this.stack.getChildren().get(0);
		Parent next = fxmlLoader.load();
		// TODO use Guice to initialize injectable members
		this.dispatchInitializedEvent();
		this.stack.getChildren().add(next);
		anim.transition(current, next);
		this.stack.getChildren().remove(0);
		this.dispatchAttachEvent();
	}

	private void dispatchInitializedEvent()
	{
		BaseController controller = this.fxmlLoader.getController();
		if (controller != null)
			controller.initialize();
	}

	private void dispatchAttachEvent()
	{
		BaseController controller = this.fxmlLoader.getController();
		if (controller != null)
			controller.attach();
	}

	private void dispatchDetachEvent()
	{
		BaseController controller = this.fxmlLoader.getController();
		if (controller != null)
			controller.detach();
	}
}
