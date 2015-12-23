package be.ehb.spg3.threading;

import be.ehb.spg3.contracts.threading.ThreadPool;

import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Created by Wannes Gennar. All rights reserved

/**
 * Manages a set of threads which can be used to submit short lived tasks without the overhead of spawning threads.
 */
public class ThreadManager implements ThreadPool
{
	ThreadPoolExecutor executor;

	public ThreadManager()
	{
		this.executor = new ThreadPoolExecutor(10,
				                                      10,
				                                      60L,
				                                      TimeUnit.SECONDS,
				                                      new SynchronousQueue<>(),
				                                      task -> new CoreThread("ThreadManager-daemon", task)
		);
	}

	/**
	 * Set the maximum amount of threads this manager may use for it's jobs.
	 *
	 * @param max The maximum amount of threads.
	 */
	@Override
	public void setMaxThreads(int max)
	{
		this.executor.setMaximumPoolSize(max);
	}

	/**
	 * Get the maximum amount of threads this manager may use for it's jobs.
	 *
	 * @return Get the maximum amount of threads.
	 */
	@Override
	public int getMaxThreads()
	{
		return this.executor.getMaximumPoolSize();
	}

	/**
	 * Submit a runnable to be executed in a separate thread.
	 * <br>
	 * <p>If all threads are busy, the task may be delayed until a thread becomes available.</p>
	 *
	 * @param task The task to be executed asynchronous.
	 */
	@Override
	public void submit(Runnable task)
	{
		this.executor.submit(task);
	}

	/**
	 * Submit a runnable to be executed in a separate thread which will yield a result.
	 * <br>
	 * <p>If all threads are busy, the task may be delayed until a thread becomes available.</p>
	 *
	 * @param task The task to be executed asynchronous.
	 * @return The result from the task when it exits.
	 */
	@Override
	public <T> Future<?> submitYielding(Runnable task)
	{
		return this.executor.submit(task);
	}

	/**
	 * Terminate all running threads.
	 */
	@Override
	public void terminate()
	{
		this.executor.shutdown();
	}
}
