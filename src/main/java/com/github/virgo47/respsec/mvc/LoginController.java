package com.github.virgo47.respsec.mvc;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.virgo47.respsec.main.restsec.TokenManager;

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
//	@Qualifier(value="kylinServiceAccountAuthProvider")
	private DaoAuthenticationProvider kylinServiceAccountAuthProvider;

	@Autowired
	private TokenManager tokenManager;

	@PostConstruct
	public void init() {
		System.out.println(" *** MainRestController.init with: " + applicationContext);
	}

	@ResponseBody
	@RequestMapping(value = "/login", produces = "text/plain")
	public String login() {
		logger.info(" *** LoginController.login");
		String username = "ZH201506006";
		String password = "c9c4c39a6ce3413ed32214ba89c1e777";
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		Authentication au = kylinServiceAccountAuthProvider.authenticate(authentication);
		logger.info(au);
		logger.info(au.getCredentials());
		logger.info(au.getPrincipal());
		logger.info(au.getDetails());
		
		return "There is nothing special about login here, just use Authorization: Basic, or provide secure token.\n" +
			"For testing purposes you can use headers X-Username and X-Password instead of HTTP Basic Access Authentication.\n" +
			"THIS APPLIES TO ANY REQUEST protected by Spring Security (see filter-mapping).\n\n" +
			"Realize, please, that Authorization request (or the one with testing X-headers) must be POST, otherwise they are ignored.";
	}

}
