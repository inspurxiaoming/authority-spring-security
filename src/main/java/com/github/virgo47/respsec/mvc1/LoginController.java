package com.github.virgo47.respsec.mvc1;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.virgo47.respsec.main.restsec.TokenManager;

import cn.jw.authority.rest.security.AuthoritiesPopulator;

/**
 * Controller with REST API. Access to login is generally permitted,
 * stuff in /secure/ sub-context is protected by config. Some security
 * annotations are thrown in just to make a point.
 */
//@RestController
@Controller
public class LoginController {

	protected static final Log logger = LogFactory.getLog(LoginController.class);
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AuthenticationProvider kylinUserAuthProvider;

	@PostConstruct
	public void init() {
		System.out.println(" *** MainRestController.init with: " + applicationContext);
	}

	@ResponseBody
	@RequestMapping(value = "/login", produces = "text/plain")
	public String login() {
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken("ZH201506006", "c9c4c39a6ce3413ed32214ba89c1e777");
		Authentication auth =  kylinUserAuthProvider.authenticate(authentication);
		
		if(auth!=null && auth.getPrincipal() != null){//通过验证
			UserDetails userContext = (UserDetails) auth.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		return "There is nothing special about login here, just use Authorization: Basic, or provide secure token.\n" +
			"For testing purposes you can use headers X-Username and X-Password instead of HTTP Basic Access Authentication.\n" +
			"THIS APPLIES TO ANY REQUEST protected by Spring Security (see filter-mapping).\n\n" +
			"Realize, please, that Authorization request (or the one with testing X-headers) must be POST, otherwise they are ignored.";
	}
	
	
	@ResponseBody
	@RolesAllowed("AUTHORITY_SYSTEM_ADMIN")
	@RequestMapping("/admin")
	public String admin() {
		System.out.println(" *** MainRestController.admin");
		return "Cool, you're admin!";
	}

}
