package com.ysd.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysd.entity.User;
import com.ysd.service.UserServiece;

/**
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	private UserServiece userServiece;//注入业务
    /**
     * 执行授权逻辑
     */
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权逻辑");
		/**
		 * 解开add方法的授权(为了解开map.put("/add", "perms[username]");//username是授权字符串)
		 */
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		/**
		 * 从数据库查询当前用户登陆的授权码
		 */
		//获取当前登陆用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		
		User dbUser = userServiece.selectQuanxian(user.getId());
		info.addStringPermission(dbUser.getQuanxian());
		return info;
	}

	/**
	 * 执行认证的逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证的逻辑");
		/**
		 * 用户名和密码登陆认证成功，才能访问他们的add，update方法(
		 * 为了解开map.put("/add", "authc");
		  map.put("/update", "authc");)
		 */
		//判断用户名和密码
		UsernamePasswordToken token=(UsernamePasswordToken)arg0;
		User user = userServiece.selectUser(token.getUsername());
		//判断用户名
		if(user==null) {
			//用户名不存在
			return null;
		}
		//判断密码		
		return new SimpleAuthenticationInfo(user, user.getPassword(), "");//第一个参数相当于User user = (User)subject.getPrincipal();
	}

}
