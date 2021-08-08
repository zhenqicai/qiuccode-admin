package cn.qiucode.cms.conf;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import cn.qiucode.cms.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @program: cms
 * @description: shiro配置类
 * @author: 上官江北
 * @create: 2021-07-25 22:34
 */

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		//filterChainDefinitionMap.put("/admin/**", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/qiu/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");
		filterChainDefinitionMap.put("/layout/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");

		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/index", "authc");
		filterChainDefinitionMap.put("/admin/**", "authc");
		filterChainDefinitionMap.put("/menu/**", "authc");
		filterChainDefinitionMap.put("/system/**", "authc");

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");

		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器
	 *（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	@Bean
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher());

		userRealm.setCachingEnabled(true);
		//启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
		userRealm.setAuthenticationCachingEnabled(false);
		//缓存AuthenticationInfo信息的缓存名称
		userRealm.setAuthenticationCacheName("authenticationCache");
		//启用授权缓存，即缓存AuthorizationInfo信息，默认false
		userRealm.setAuthorizationCachingEnabled(true);
		//缓存AuthorizationInfo信息的缓存名称
		userRealm.setAuthorizationCacheName("authorizationCache");

		return userRealm;
	}


	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//自定义realm
		securityManager.setRealm(userRealm());
		return securityManager;
	}

	/**
	 * 开启shiro aop注解支持.
	 * 使用代理方式;所以需要开启代码支持;
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}



	@Bean
	public SimpleCookie cookie() {
		SimpleCookie cookie = new SimpleCookie("QIUSESSIONID"); //  cookie的name,对应的默认是 JSESSIONID
		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		cookie.setPath("/");        //  path为 / 用于多个系统共享JSESSIONID
		return cookie;
	}

	/**
	 * Session Manager
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		//单位是毫秒
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setGlobalSessionTimeout(60 * 30 * 1000);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionValidationInterval(60 * 30 * 1000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionIdCookie(cookie());
		sessionManager.setSessionIdCookieEnabled(true);


		return sessionManager;
	}


	/**
	 * 解决： 无权限页面不跳转 shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized") 无效
	 * shiro的源代码ShiroFilterFactoryBean.Java定义的filter必须满足filter instanceof AuthorizationFilter，
	 * 只有perms，roles，ssl，rest，port才是属于AuthorizationFilter，而anon，authcBasic，auchc，user是AuthenticationFilter，
	 * 所以unauthorizedUrl设置后页面不跳转 Shiro注解模式下，登录失败与没有权限都是通过抛出异常。
	 * 并且默认并没有去处理或者捕获这些异常。在SpringMVC下需要配置捕获相应异常来通知用户信息
	 *
	 * @return
	 */
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
//		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException", "/user/403");
		r.setExceptionMappings(mappings);  // None by default
//		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("exception");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}
}




/*@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public Realm realm() {
		return new UserRealm();
	}
	
	 //**
     //* 用户授权信息Cache
     //*
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public DefaultSecurityManager securityManager() {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setCacheManager(cacheManager());
        return sm;
    }

	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
		securityManager.setRealm(realm);

		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/login");//默认也是这个  
		shiroFilter.setSuccessUrl("/main/indexMain");
		shiroFilter.setUnauthorizedUrl("/common/403");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/anno/**", "anon");
		filterChainDefinitionMap.put("/logout", "anon");
		//动态授权的资源
		filterChainDefinitionMap.put("/sys/user/index", "perms[system:user:index]");
		filterChainDefinitionMap.put("/sys/user/add", "perms[system:user:add]");
		filterChainDefinitionMap.put("/sys/user/edit*", "perms[system:user:edit]");
		filterChainDefinitionMap.put("/sys/user/deleteBatch", "perms[system:user:deleteBatch]");
		filterChainDefinitionMap.put("/sys/user/grant/**", "perms[system:user:grant]");
		
		filterChainDefinitionMap.put("/sys/role/index", "perms[system:role:index]");
		filterChainDefinitionMap.put("/sys/role/add", "perms[system:role:add]");
		filterChainDefinitionMap.put("/sys/role/edit*", "perms[system:role:edit]");
		filterChainDefinitionMap.put("/sys/role/deleteBatch", "perms[system:role:deleteBatch]");
		filterChainDefinitionMap.put("/sys/role/grant/**", "perms[system:role:grant]");
		
		filterChainDefinitionMap.put("/sys/resource/index", "perms[system:resource:index]");
		filterChainDefinitionMap.put("/sys/resource/add", "perms[system:resource:add]");
		filterChainDefinitionMap.put("/sys/resource/edit*", "perms[system:resource:edit]");
		filterChainDefinitionMap.put("/sys/resource/deleteBatch", "perms[system:resource:deleteBatch]");
		
		filterChainDefinitionMap.put("/sys/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}*/
