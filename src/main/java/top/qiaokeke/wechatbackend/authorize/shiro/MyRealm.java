package top.qiaokeke.wechatbackend.authorize.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.authorize.database.UserBean;
import top.qiaokeke.wechatbackend.authorize.database.UserService;
import top.qiaokeke.wechatbackend.authorize.util.JWTUtil;
import top.qiaokeke.wechatbackend.dataaccess.entity.AuthUser;
import top.qiaokeke.wechatbackend.dataaccess.entity.AuthUserRole;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.repository.AuthUserRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.AuthUserRoleRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.SysUserRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.ISysUserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

    Logger logger = LoggerFactory.getLogger(MyRealm.class);


    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    AuthUserRoleRepository authUserRoleRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        List<AuthUserRole> authUserRoles = authUserRoleRepository.getAuthUserRoleByAuid(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (AuthUserRole role: authUserRoles){
            simpleAuthorizationInfo.addRole(role.getRoleType().name());
        }

        //Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        //simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        AuthUser authUser = authUserRepository.getAuthUserByAuid(username);
        if(authUser == null ){
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, authUser.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
