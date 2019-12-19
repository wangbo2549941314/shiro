package com.ysd.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ysd.entity.User;

@Mapper
public interface UserMapper {
	//登陆验证
	User selectUser(String name);
	//权限分配
	User selectQuanxian(Integer id);

}
