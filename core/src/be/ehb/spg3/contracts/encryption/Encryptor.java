package be.ehb.spg3.contracts.encryption;

// Created by Wannes Gennar. All rights reserved

/**
 * Provides a contract for a generic cryptography module.
 */
public interface Encryptor
{
	/**
	 * Encrypt a string.
	 *
	 * @param str The string to encrypt.
	 * @return The encrypted string.
	 */
	String encrypt(String str);

	/**
	 * Decrypt an encrypted string.
	 *
	 * @param str The string to decrypt.
	 * @return The decrypted string.
	 */
	String decrypt(String str);

	/**
	 * Calculate the hash of a string.
	 *
	 * @param str The string to hash.
	 * @return The digest of the hash.
	 */
	String hash(String str);
}
