package be.ehb.spg3.events;

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.errors.ErrorEvent;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;

// Created by Wannes Gennar. All rights reserved

/**
 * Eventbus implementation for MBassador
 */
public class MBassadorBus implements EventBus
{
	private MBassador eventbus;

	public MBassadorBus()
	{
		this.eventbus = new MBassador(new BusConfiguration()
				                              .addFeature(Feature.SyncPubSub.Default())
				                              .addFeature(Feature.AsynchronousHandlerInvocation.Default())
				                              .addFeature(Feature.AsynchronousMessageDispatch.Default())
				                              .addPublicationErrorHandler(error -> this.fire(new ErrorEvent((Exception) error.getCause())))
		); // TODO error handler
	}

	/**
	 * Register an event listener in the event bus.
	 *
	 * @param listener The listener to register.
	 */
	@Override
	public void subscribe(Object listener)
	{
		this.eventbus.subscribe(listener);
	}

	/**
	 * Unregister an event listener in the event bus.
	 *
	 * @param listener The listener to remove.
	 */
	@Override
	public void unsubscribe(Object listener)
	{
		this.eventbus.unsubscribe(listener);
	}

	/**
	 * Fire an event asynchronous.
	 *
	 * @param event The event to fire.
	 */
	@Override
	public <T> void fireAsynchronous(T event)
	{
		this.eventbus.post(event).asynchronously();
	}

	/**
	 * Fire an event synchrone.
	 *
	 * @param event The event to fire.
	 */
	@Override
	public <T> void fireSynchronous(T event)
	{
		this.eventbus.post(event).now();
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
}
