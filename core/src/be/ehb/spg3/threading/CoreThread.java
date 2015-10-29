package be.ehb.spg3.threading;

// Created by Wannes Gennar. All rights reserved
public class CoreThread extends Thread
{
	private static UncaughtExceptionHandler handler = null;

	public CoreThread(Runnable task)
	{
		super(task);
	}

	@Override
	public void run()
	{
		super.run();
		if (handler != null)
			this.setUncaughtExceptionHandler(handler);
	}

	public static UncaughtExceptionHandler getHandler()
	{
		return handler;
	}

	public static void setHandler(UncaughtExceptionHandler handler)
	{
		CoreThread.handler = handler;
	}
}
