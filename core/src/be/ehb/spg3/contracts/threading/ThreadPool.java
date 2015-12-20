package be.ehb.spg3.contracts.threading;

import java.util.concurrent.Future;

// Created by Wannes Gennar. All rights reserved

/**
 * Manages a set of threads which can be used to submit short lived tasks without the overhead of spawning threads.
 */
public interface ThreadPool
{
	/**
	 * Set the maximum amount of threads this manager may use for it's jobs.
	 *
	 * @param max The maximum amount of threads.
	 */
	void setMaxThreads(int max);

	/**
	 * Get the maximum amount of threads this manager may use for it's jobs.
	 *
	 * @return Get the maximum amount of threads.
	 */
	int getMaxThreads();

	/**
	 * Submit a runnable to be executed in a separate thread.
	 * <br>
	 * <p>If all threads are busy, the task may be delayed until a thread becomes available.</p>
	 *
	 * @param task The task to be executed asynchronous.
	 */
	void submit(Runnable task);

	/**
	 * Submit a runnable to be executed in a separate thread which will yield a result.
	 * <br>
	 * <p>If all threads are busy, the task may be delayed until a thread becomes available.</p>
	 *
	 * @param task The task to be executed asynchronous.
	 * @return The result from the task when it exits.
	 */
	<T> Future<?> submitYielding(Runnable task);

	/**
	 * Terminate all running threads.
	 */
	void terminate();
}
