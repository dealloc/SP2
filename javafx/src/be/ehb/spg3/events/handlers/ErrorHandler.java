package be.ehb.spg3.events.handlers;

import be.ehb.spg3.events.errors.ErrorEvent;
import net.engio.mbassy.listener.Handler;
import org.controlsfx.dialog.ExceptionDialog;

// Created by Wannes Gennar. All rights reserved
public class ErrorHandler
{
	@Handler
	public void handle(ErrorEvent event)
	{
		event.getException().printStackTrace();

		new ExceptionDialog(event.getException()).show();
	}
}
