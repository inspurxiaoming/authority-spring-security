package cn.jw.authority.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;


public class MyAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

	private AuthenticationProvider authenticationProvider;

	public MyAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		super();
		Assert.notNull(authenticationProvider, "The embedded authenticationProvider should not be null.");
		this.authenticationProvider = authenticationProvider;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    Authentication authed = null;
	    try {
	    	authed = authenticationProvider.authenticate(authentication);
	    	if(authed != null && authed.getPrincipal() != null){//通过验证
				SecurityContextHolder.getContext().setAuthentication(authed);
			}
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
}
