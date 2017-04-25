/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.jw.authority.rest.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticationProvider;
import org.springframework.util.Assert;

/**
 * A wrapper class for the authentication provider; Will do something more for
 * Kylin.
 */
public class MyAuthenticationProvider extends AbstractLdapAuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

	// Embedded authentication provider
	private AuthenticationProvider authenticationProvider;

	MessageDigest md = null;

	public MyAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		super();
		Assert.notNull(authenticationProvider, "The embedded authenticationProvider should not be null.");
		this.authenticationProvider = authenticationProvider;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Failed to init Message Digest ", e);
		}
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    Authentication authed = null;
	    try {
	    	authed = authenticationProvider.authenticate(authentication);
		} catch (AuthenticationException e) {
            logger.error("Failed to auth user: " + authentication.getName(), e);
            throw e;
		}
		return authed;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authenticationProvider.supports(authentication);
	}

	public AuthenticationProvider getAuthenticationProvider() {
		return authenticationProvider;
	}

	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	protected DirContextOperations doAuthentication(UsernamePasswordAuthenticationToken auth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<? extends GrantedAuthority> loadUserAuthorities(DirContextOperations userData, String username,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}



}
