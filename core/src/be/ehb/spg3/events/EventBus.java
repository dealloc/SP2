package be.ehb.spg3.events;

import be.ehb.spg3.contracts.events.Handler;
import be.ehb.spg3.contracts.threading.ThreadPool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

// Created by Wannes Gennar. All rights reserved

/**
 * A custom implementation of the EventBus contract.
 */
public class EventBus implements be.ehb.spg3.contracts.events.EventBus
{
	private ArrayList<Object> listeners;

	/**
	 * Register an event listener in the event bus.
	 *
	 * @param listener The listener to register.
	 */
	@Override
	public void subscribe(Object listener)
	{
		this.listeners.add(listener);
	}

	/**
	 * Unregister an event listener in the event bus.
	 *
	 * @param listener The listener to remove.
	 */
	@Override
	public void unsubscribe(Object listener)
	{
		this.listeners.remove(listener);
	}

	/**
	 * Fire an event asynchronous.
	 *
	 * @param event The event to fire.
	 */
	@Override
	public <T> void fireAsynchronous(T event)
	{
		resolve(ThreadPool.class).submit(() -> this.fireSynchronous(event));
	}

	/**
	 * Fire an event synchrone.
	 *
	 * @param event The event to fire.
	 */
	@Override
	public <T> void fireSynchronous(T event)
	{
		for (Object listener : this.listeners)
		{
			Arrays.stream(listener.getClass().getMethods()).parallel().filter(this::annotatedWith).forEach(method ->
			{
				try
				{
					method.invoke(event);
				}
				catch (ReflectiveOperationException e)
				{
					e.printStackTrace(); // TODO figure out what to do with this
				}
			});
		}
	}

	/**
	 * Fire an event with the default convention.
	 *
	 * @param event The event to fire.
	 */
	@Override
	public <T> void fire(T event)
	{
		this.fireAsynchronous(event);
	}

	private boolean annotatedWith(Method method)
	{
		return method.isAnnotationPresent(Handler.class);
	}
}
