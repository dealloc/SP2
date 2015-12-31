package be.ehb.spg3.events.handlers;

import be.ehb.spg3.events.errors.ErrorEvent;
import net.engio.mbassy.listener.Handler;
import org.controlsfx.control.Notifications;

// Created by Wannes Gennar. All rights reserved
public class ErrorHandler
{
	@Handler
	public void handle(ErrorEvent event)
	{
		event.getException().printStackTrace();
		Notifications.create()
				.title("An internal error occured")
				.text(event.getException().getLocalizedMessage())
				.darkStyle()
				.showError();
	}
}
