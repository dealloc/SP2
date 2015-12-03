package be.ehb.spg3.contracts.events;

// Created by Wannes Gennar. All rights reserved

/**
 * Provides a generic contract to an event bus.
 */
public interface EventBus
{
	/**
	 * Register an event listener in the event bus.
	 *
	 * @param listener The listener to register.
	 */
	void subscribe(Object listener);

	/**
	 * Unregister an event listener in the event bus.
	 *
	 * @param listener The listener to remove.
	 */
	void unsubscribe(Object listener);

	/**
	 * Fire an event asynchronous.
	 *
	 * @param event The event to fire.
	 * @param <T>   The event type.
	 */
	<T> void fireAsynchronous(T event);

	/**
	 * Fire an event synchrone.
	 *
	 * @param event The event to fire.
	 * @param <T>   The event type.
	 */
	<T> void fireSynchronous(T event);

	/**
	 * Fire an event with the default convention.
	 *
	 * @param event The event to fire.
	 * @param <T>   The event type.
	 */
	<T> void fire(T event);
}
