package be.ehb.spg3.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

// Created by Wannes Gennar. All rights reserved.

/**
 * Wrapper around the transitions between screens.
 */
public abstract class TransitionAnimation
{
	/**
	 * No transition between screens.
	 */
	public static final TransitionAnimation NONE = new NoTransition();

	/**
	 * Simple fade animation.
	 */
	public static final TransitionAnimation FADE_IN = new FadeTransition();

	/**
	 * Perform the transition between the current and the next node.
	 *
	 * @param current The currently visible node that should be hidden.
	 * @param next    The next node that should be shown.
	 */
	public abstract void transition(Node current, Node next);

	private static class NoTransition extends TransitionAnimation
	{
		public void transition(Node current, Node next)
		{
			current.setVisible(false);
			next.setVisible(true);
		}
	}

	private static class FadeTransition extends TransitionAnimation
	{
		public void transition(Node current, Node next)
		{
			Timeline fadeout = new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(current.opacityProperty(), 1)),
					new KeyFrame(Duration.seconds(1), new KeyValue(current.opacityProperty(), 0))
			);
			Timeline fadein = new Timeline(
					new KeyFrame(Duration.seconds(1), new KeyValue(next.opacityProperty(), 0)),
					new KeyFrame(Duration.ZERO, new KeyValue(next.opacityProperty(), 1))
			);
			fadeout.play();
			fadein.play();
		}
	}
}
