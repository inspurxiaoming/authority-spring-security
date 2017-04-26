package cn.jw.authority.rest.security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
/**
 * 
 * @author jointwisdom
 *
 */
public class AuthoritiesPopulator extends DefaultLdapAuthoritiesPopulator {
    private static final Logger logger = LoggerFactory.getLogger(AuthoritiesPopulator.class);

    String adminRole;
    SimpleGrantedAuthority adminRoleAsAuthority;

    //系统中存在的两个角色ROLE_AUTHORITY_SYSTEM_ADMIN ROLE_AUTHORITY_SYSTEM_USER
    //定义权限申请系统中的连个权限组，即角色，管理员和普通用户
    SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(Constant.ROLE_AUTHORITY_SYSTEM_ADMIN);
    SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(Constant.ROLE_AUTHORITY_SYSTEM_USER);

    Set<GrantedAuthority> defaultAuthorities = new HashSet<GrantedAuthority>();

    /**
     * 初始化角色和权限关系, 通过spring-security.xml初始化
     * @param contextSource
     * @param groupSearchBase LDAP中GROUP的基础路径
     */
    public AuthoritiesPopulator(ContextSource contextSource, String groupSearchBase, String adminRole, String defaultRole) {
        super(contextSource, groupSearchBase);
        this.adminRole = adminRole;
        this.adminRoleAsAuthority = new SimpleGrantedAuthority(adminRole);

        if (defaultRole.contains(Constant.ROLE_AUTHORITY_SYSTEM_USER)){
        	this.defaultAuthorities.add(userAuthority);
        }
    }

    /**
     * 用户所对应的权限组，在LDAP中查询，获取member=${userDn}的所有Group信息
     * 这个方法中定义，如果一个user是某个Group的member,则该用户就对应相应的权限
     */
    @Override
    public Set<GrantedAuthority> getGroupMembershipRoles(String userDn, String username) {
        //通过userDn查询 用户所在的GROUP
        Set<GrantedAuthority> authorities = super.getGroupMembershipRoles(userDn, username);

        //如果user拥有adminRole，则赋所有权限 
        if (authorities.contains(adminRoleAsAuthority)) {
            authorities.add(adminAuthority);
            authorities.add(userAuthority);
        }
        //默认权限
        authorities.addAll(defaultAuthorities);
        return authorities;
    }

}
