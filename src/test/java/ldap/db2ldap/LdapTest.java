package ldap.db2ldap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
    "classpath:spring-security.xml"
})
public class LdapTest {
//	@Autowired
//	private AuthenticationProvider kylinUserAuthProvider;
	
//	@Test
//	public void springAuth(){
//		UsernamePasswordAuthenticationToken authentication = 
//				new UsernamePasswordAuthenticationToken("ZH201506006", "c9c4c39a6ce3413ed32214ba89c1e777");
//		Authentication auth =  kylinUserAuthProvider.authenticate(authentication);
//		
//		if(auth!=null && auth.getPrincipal() != null){//通过验证
//			UserDetails userContext = (UserDetails) auth.getPrincipal();
//			SecurityContextHolder.getContext().setAuthentication(auth);
//		}
//		System.out.println(1);
//	} 
	
	@Test
	public void testMD5(){
		String pwd = "147637";
		System.out.println(DigestUtils.md5Hex(pwd));
	}
}
