package be.ehb.spg3.events.errors;

// Created by Wannes Gennar. All rights reserved
public class ErrorEvent
{
	private final Exception ex;

	public ErrorEvent(Exception ex)
	{
		this.ex = ex;
	}

	public Exception getException()
	{
		return ex;
	}
}
