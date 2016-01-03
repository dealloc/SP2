package be.ehb.spg3;

// Created by Wannes Gennar. All rights reserved

import be.ehb.spg3.contracts.events.EventBus;
import be.ehb.spg3.events.errors.ErrorEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

/**
 * Provides simplified access to resources such as FXML, CSS, images and other media.
 */
public final class Resources
{
	private static FXMLLoader loader;

	// private constructor to prevent instantiation
	private Resources()
	{
	}

	/**
	 * Load a resource from a relative string, package formatted string.
	 *
	 * @param url The url to load the resource from.
	 * @return An URL to the given resource or null if the resource couldn't be found.
	 */
	public static URL load(String url)
	{
		url = Resources.transform(url);
		return Resources.class.getResource(url);
	}

	/**
	 * Load an FXML file from a relative package formatted string.
	 *
	 * @param url The url to load the FXML file from.
	 * @param <T> The type of Node to load.
	 * @return The loaded FXML file or null if loading failed.
	 */
	public static <T> T fxml(String url)
	{
		URL location = load(url);
		loader = new FXMLLoader(location);

		try
		{
			return loader.load();
		}
		catch (IOException e)
		{
			resolve(EventBus.class).fire(new ErrorEvent(e));
			return null;
		}
	}

	public static <T> T controller()
	{
		return (T) loader.getController();
	}

	private static String transform(String url)
	{
		String extension = url.substring(url.lastIndexOf("."));
		url = url.substring(0, url.lastIndexOf("."));
		return "design/" + url.replaceAll("\\.", "/") + extension;
	}
}
