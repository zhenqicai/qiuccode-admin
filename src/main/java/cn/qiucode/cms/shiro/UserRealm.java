package cn.qiucode.cms.shiro;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.AdminUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: cms
 * @description: 自定义Realm类
 * @author: 上官江北
 * @create: 2021-07-25 22:34
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserService adminUserService;

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //如果授权部分没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要AdminUser对象
        AdminUser user = (AdminUser) principals.getPrimaryPrincipal();

        //后续设置角色
        adminUserService.doGetUserAuthorizationInfo(user);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(user.getRoles());
        simpleAuthorizationInfo.setStringPermissions(user.getStringPermissions());
        return simpleAuthorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        if (userName == null)
            return null;
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        AdminUser user=adminUserService.findByName(userName);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
