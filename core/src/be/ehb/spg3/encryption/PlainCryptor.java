package be.ehb.spg3.encryption;

import be.ehb.spg3.contracts.encryption.Encryptor;

// Created by Wannes Gennar. All rights reserved

/**
 * Implementation of Encryptor that returns all given strings back plain text.
 */
public class PlainCryptor implements Encryptor
{
	/**
	 * Encrypt a string.
	 *
	 * @param str The string to encrypt.
	 * @return The encrypted string.
	 */
	@Override
	public String encrypt(String str)
	{
		return str;
	}

	/**
	 * Decrypt an encrypted string.
	 *
	 * @param str The string to decrypt.
	 * @return The decrypted string.
	 */
	@Override
	public String decrypt(String str)
	{
		return str;
	}

	/**
	 * Calculate the hash of a string.
	 *
	 * @param str The string to hash.
	 * @return The digest of the hash.
	 */
	@Override
	public String hash(String str)
	{
		return str;
	}
}
