package be.ehb.spg3;

// Created by Wannes Gennar. All rights reserved

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

/**
 * Provides simplified access to resources such as FXML, CSS, images and other media.
 */
public final class Resources
{
	private Resources()
	{
	} // private constructor to prevent instantiation

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

		try
		{
			return FXMLLoader.load(location);
		}
		catch (IOException e)
		{
			return null;
		}
	}

	private static String transform(String url)
	{
		return "design/" + url.replaceAll("\\.", "/");
	}
}
