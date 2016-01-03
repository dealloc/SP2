package be.ehb.spg3.events;

// Created by Wannes Gennar. All rights reserved
public class PopupEvent
{
	private final String url;

	public PopupEvent(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public static final class ClosePopupEvent
	{
	}
}
