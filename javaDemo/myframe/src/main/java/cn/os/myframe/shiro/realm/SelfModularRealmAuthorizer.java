package cn.os.myframe.shiro.realm;

import cn.os.myframe.shiro.ShiroConstant;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * @author oushuo
 * @date 2020/10/20
 * @description 自定义授权器
 */
public class SelfModularRealmAuthorizer extends ModularRealmAuthorizer {

    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        Set<String> realmNames = principals.getRealmNames();
        String realmName = realmNames.iterator().next();
        if (ShiroConstant.REALM_NAME.equals(realmName)) {
            return getAuthorizerRealm().isPermitted(principals,roleIdentifier);
        }
        return true;
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        Set<String> realmNames = principals.getRealmNames();
        String realmName = realmNames.iterator().next();
        if (ShiroConstant.REALM_NAME.equals(realmName)) {
            return getAuthorizerRealm().isPermitted(principals,permission);
        }
        return true;
    }

    /**
     * 授权Reaml
     * @return
     */
    private ShiroRealm getAuthorizerRealm() {
        return new ShiroRealm();
    }


}
