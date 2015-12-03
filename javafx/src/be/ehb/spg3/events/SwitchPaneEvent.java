package be.ehb.spg3.events;

import be.ehb.spg3.Main;

import java.net.URL;

// Created by Wannes Gennar. All rights reserved
public class SwitchPaneEvent
{
	private URL location;

	public SwitchPaneEvent(String location)
	{
		this.location = Main.class.getResource(location);
	}

	public URL getLocation()
	{
		return location;
	}
}
