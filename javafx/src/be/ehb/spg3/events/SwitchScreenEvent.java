package be.ehb.spg3.events;

import be.ehb.spg3.Main;

import java.net.URL;

// Created by Wannes Gennar. All rights reserved
public final class SwitchScreenEvent
{
	private URL location;
	private boolean fullscreen;

	public SwitchScreenEvent(String screen, boolean fullscreen)
	{
		this.location = Main.class.getResource(screen);
		this.fullscreen = fullscreen;
	}

	public URL getLocation()
	{
		return location;
	}

	public boolean getFullscreen()
	{
		return fullscreen;
	}
}
