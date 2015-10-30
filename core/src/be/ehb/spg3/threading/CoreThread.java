package be.ehb.spg3.threading;

// Created by Wannes Gennar. All rights reserved

/**
 * Provides a thin layer around native Java threads.
 * CoreThreads automaticly install an UncaughtExceptionHandler if available.
 */
public class CoreThread extends Thread
{
	private static UncaughtExceptionHandler handler = null;

	public CoreThread(Runnable task)
	{
		super(task);
	}

	/**
	 * Start the thread and install the UncaughtExceptionHandler if available.
	 */
	@Override
	public void run()
	{
		super.run();
		if (handler != null)
			this.setUncaughtExceptionHandler(handler);
	}

	/**
	 * Retrieve the UncaughtExceptionHandler that's currently registered for CoreThreads.
	 * @return The UncaughtExceptionHandler.
	 */
	public static UncaughtExceptionHandler getHandler()
	{
		return handler;
	}

	/**
	 * Set a new UncaughtExceptionHandler for all newly started CoreaThreads
	 * @param handler the new UncaughtExceptionHandler.
	 */
	public static void setHandler(UncaughtExceptionHandler handler)
	{
		CoreThread.handler = handler;
	}
}
