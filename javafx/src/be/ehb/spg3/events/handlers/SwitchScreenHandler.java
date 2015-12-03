package be.ehb.spg3.events.handlers;

import be.ehb.spg3.events.SwitchScreenEvent;
import net.engio.mbassy.listener.Handler;

// Created by Wannes Gennar. All rights reserved
public class SwitchScreenHandler
{
	@Handler
	public void handle(SwitchScreenEvent event)
	{
		System.out.println(event.getLocation().toString());
	}
}
