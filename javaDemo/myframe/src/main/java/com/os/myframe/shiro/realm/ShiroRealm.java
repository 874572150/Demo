package com.os.myframe.shiro.realm;

import com.os.myframe.model.Permissions;
import com.os.myframe.model.Role;
import com.os.myframe.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author oushuo
 * @date 2020/10/9
 * @description TODO
 */
public class ShiroRealm extends AuthorizingRealm{

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        User currUser = (User) principalCollection.getPrimaryPrincipal();
        User user = new User();
        user.setAccount("zs");
        user.setPassword("123");
        Role role = new Role();
        role.setRoleName("admin");
        Set<Permissions> perms = new HashSet<>();
        Permissions perm = new Permissions();
        perm.setPermissionsUrl("/user/index");
        perms.add(perm);
        role.setPermissions(perms);
        user.setRole(role);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole().getRoleName());
        role.getPermissions().forEach(permission -> {
            simpleAuthorizationInfo.addStringPermission(perm.getPermissionsUrl());
        });
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String account = (String) authenticationToken.getPrincipal();
        //
        User user = new User();
        user.setAccount("zs");
        user.setPassword("123");
        if (user != null) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(account, user.getPassword(), this.getName());
            return simpleAuthenticationInfo;
        } else {
            return null;
        }
    }
}
