package be.ehb.spg3.encryption;

import be.ehb.spg3.contracts.encryption.Hasher;
import org.jasypt.digest.StandardStringDigester;

// Created by Wannes Gennar. All rights reserved
public class Sha1Cryptor implements Hasher
{
	private StandardStringDigester digester;

	public Sha1Cryptor()
	{
		this.digester = new StandardStringDigester();
		this.digester.setAlgorithm("SHA-1");   // optionally set the algorithm
		this.digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
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
		return digester.digest(str);
	}

	/**
	 * Check if a string is already hashed or not.
	 *
	 * @param str The possible hash.
	 * @return true if the string is a hash, false if it isn't.
	 */
	@Override
	public boolean needsRehash(String str)
	{
		return !str.matches("[a-fA-F0-9]{40}");
	}

	/**
	 * Check if a string matches a hash.
	 *
	 * @param hash  The hash to compare.
	 * @param plain The text to compare.
	 * @return true if the plain text matches the hash, false if not.
	 */
	@Override
	public boolean check(String hash, String plain)
	{
		return this.digester.matches(plain, hash);
	}
}
