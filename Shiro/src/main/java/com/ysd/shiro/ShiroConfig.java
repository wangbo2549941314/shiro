package com.ysd.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro的配置类
 * 
 */
@Configuration
public class ShiroConfig {
	/**
	 * 创建 ShiroFilterFactoryBean
	 * 
	 */
    @Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
		@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器(关联SecurityManager)
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//设置内置过滤器
		/**shiro内置过滤器，可以实现权限相关的拦截器
		常用的过滤器：
		1.anon：无需认证（登陆可以访问）
		2.authc：必须认证才可以访问
		3.user：如果使用remeberMe的功能可以直接访问
		4.perms：该资源必须得到资源权限才可以访问
		5.role：该资源必须得到角色权限才可以访问 **/
		Map<String, String> map=new LinkedHashMap<String, String>();
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		//authc只有认证过后才能访问的页面
		map.put("/add", "authc");
		map.put("/update", "authc");
		//当add页面，update页面需要认证过后才能访问，认证之前转交的页面
		shiroFilterFactoryBean.setLoginUrl("/tologin");
		
		//perms授权过滤器
		//注意：当前授权拦截后，shiro会自动跳转到未授权页面
		map.put("/add", "perms[userAdd]");//userAdd是授权字符串,与数据库权限字段内容保持一致
		map.put("/update", "perms[userUpdate]");
		//设置未授权转交的页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
		return shiroFilterFactoryBean;

	}

	/**
	 * 创建 DefaultWebSecurityManager
	 * 
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 管理realm(关联Realm)
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	/**
	 * 创建 Realm
	 * 
	 */
	@Bean(name = "userRealm") // bean注解作用：就是将我们的方法放入spring环境中
	public UserRealm getRealm() {
		return new UserRealm();
	}
	
	/**
	 * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}

}
