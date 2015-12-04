package be.ehb.spg3.events;

// Created by Wannes Gennar. All rights reserved
public class SwitchPaneEvent
{
	private String location;

	public SwitchPaneEvent(String location)
	{
		this.location = location;
	}

	public String getLocation()
	{
		return location;
	}
}
