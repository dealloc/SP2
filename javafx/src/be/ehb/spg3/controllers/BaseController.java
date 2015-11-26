package be.ehb.spg3.controllers;

// Created by Wannes Gennar. All rights reserved

/**
 * Simple class for managing the lifecycle of a screen in the JavaFX application.
 * This class can be compared to the Activity class of Android.
 * We use a very simple lifecycle; initialize -> attach -> detach.
 */
public abstract class BaseController
{
	/**
	 * Called when controller is loaded and all fields are statisfied.
	 * All event handlers etc should be attached in this stage.
	 */
	public void initialize()
	{
	}

	/**
	 * Called when the associated screen is attached to the document tree.
	 * This method is called every time the screen is made visible again. (ex after hiding it)
	 */
	public void attach()
	{
	}

	/**
	 * Called when the associated screen is about to be detached from the document tree.
	 * This method is called every time the screen is being hidden. (ex when sswitching to another screen).
	 * All event handlers should be detached in this stage.
	 */
	public void detach()
	{
	}
}
