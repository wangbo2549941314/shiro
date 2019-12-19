package com.ysd.service;

import com.ysd.entity.User;

public interface UserServiece {
	   //登陆验证
		User selectUser(String name);
		//权限分配
		User selectQuanxian(Integer id);

}
