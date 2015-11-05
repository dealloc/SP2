package be.ehb.spg3.realms.database;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// Created by Wannes Gennar. All rights reserved

/**
 * A simple Realm that connects to the database and retrieves the required information
 *
 * @apiNote heavily inspired by https://github.com/apache/shiro/blob/1.0.x/samples/standalone/src/main/java/MyRealm.java
 */
public class CoreRealm extends AuthorizingRealm
{
	private SimpleAccount getAccount(String username)
	{
		// TODO get the account details from the database
		return new SimpleAccount(username, "verylongencryptedpasswordfromthedatabase", getName());
	}

	/**
	 * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
	 * authentication token.
	 * <p>
	 * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
	 * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
	 * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
	 * <p>
	 * A {@code null} return value means that no account could be associated with the specified token.
	 *
	 * @param token the authentication token containing the user's principal and credentials.
	 * @return an {@link AuthenticationInfo} object containing account data resulting from the
	 * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
	 * @throws AuthenticationException if there is an error acquiring data or performing
	 *                                 realm-specific authentication logic for the specified <tt>token</tt>
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		UsernamePasswordToken credentials = (UsernamePasswordToken) token;
		return this.getAccount(credentials.getUsername());
	}

	/**
	 * Retrieves the AuthorizationInfo for the given principals from the underlying data store.  When returning
	 * an instance from this method, you might want to consider using an instance of
	 * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
	 *
	 * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
	 * @return the AuthorizationInfo associated with this principals.
	 * @see SimpleAuthorizationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		String username = (String) getAvailablePrincipal(principals);
		return this.getAccount(username);
	}
}