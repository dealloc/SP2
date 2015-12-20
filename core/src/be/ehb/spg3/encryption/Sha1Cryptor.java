package be.ehb.spg3.encryption;

import be.ehb.spg3.contracts.encryption.Hasher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Created by Wannes Gennar. All rights reserved

/**
 * An implementation of the be.ehb.spg3.contracts.encryption.Hasher using SHA-1 as hashing algorithm.
 */
public class Sha1Cryptor implements Hasher
{
	private final MessageDigest digester;

	public Sha1Cryptor() throws NoSuchAlgorithmException
	{
		this.digester = MessageDigest.getInstance("SHA-1");
	}

	/**
	 * Calculate the hash of a string.
	 * <br>
	 * <p>See http://www.anyexample.com/programming/java/java_simple_class_to_compute_sha_1_hash.xml</p>
	 * @param str The string to hash.
	 * @return The digest of the hash.
	 */
	@Override
	public String hash(String str)
	{
		byte[] bytes = str.getBytes();
		this.digester.update(bytes);
		byte[] hash = this.digester.digest();

		StringBuilder buffer = new StringBuilder();
		for (byte aHash : hash)
		{
			int halfbyte = (aHash >>> 4) & 0x0F;
			int two_halfs = 0;
			do
			{
				if ((0 <= halfbyte) && (halfbyte <= 9))
				{
					buffer.append((char) ('0' + halfbyte));
				}
				else
				{
					buffer.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = aHash & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buffer.toString();
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
		return this.hash(plain).equals(hash);
	}
}
