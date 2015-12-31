package be.ehb.spg3.events.handlers;

import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.application.Platform;
import net.engio.mbassy.listener.Handler;
import org.controlsfx.dialog.ExceptionDialog;

// Created by Wannes Gennar. All rights reserved
public class ErrorHandler
{
	@Handler
	public void handle(ErrorEvent event)
	{
		event.getException().printStackTrace();

		Platform.runLater(() -> new ExceptionDialog(event.getException()).show());
	}
}
