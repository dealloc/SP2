package be.ehb.spg3.contracts.encryption;

// Created by Wannes Gennar. All rights reserved

/**
 * Provides a contract for a generic cryptography module.
 */
public interface Hasher
{
	/**
	 * Calculate the hash of a string.
	 *
	 * @param str The string to hash.
	 * @return The digest of the hash.
	 */
	String hash(String str);

	/**
	 * Check if a string is already hashed or not.
	 *
	 * @param str The possible hash.
	 * @return true if the string is a hash, false if it isn't.
	 */
	boolean needsRehash(String str);

	/**
	 * Check if a string matches a hash.
	 *
	 * @param hash  The hash to compare.
	 * @param plain The text to compare.
	 * @return true if the plain text matches the hash, false if not.
	 */
	boolean check(String hash, String plain);
}
