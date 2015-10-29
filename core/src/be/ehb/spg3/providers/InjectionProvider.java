package be.ehb.spg3.providers;

import com.google.inject.Guice;
import com.google.inject.Injector;

// Created by Wannes Gennar. All rights reserved

/**
 * Wrapper around the internal IoC container.
 * This class is solely responsible for resolving types through the (internal) IoC
 */
public class InjectionProvider
{
	private static Injector injector = null;

	private static void initialize()
	{
		InjectionProvider.injector = Guice.createInjector(new BindingProvider());
	}

	/**
	 * Resolve an instance of given type through the IoC container
	 *
	 * @param type The type to resolve.
	 * @param <T>  The type to resolve.
	 * @return An instance of T resolved through the IoC.
	 */
	public static <T> T resolve(Class<? extends T> type)
	{
		if (injector == null)
			InjectionProvider.initialize();

		return InjectionProvider.injector.getInstance(type);
	}
}
