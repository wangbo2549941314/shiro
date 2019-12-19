package com.ysd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysd.dao.UserMapper;
import com.ysd.entity.User;
@Service
public class UserServieceImpl implements UserServiece{
	@Autowired
	private UserMapper userMapper;
	
	public User selectUser(String name) {
		User selectUser = userMapper.selectUser(name);
		return selectUser;
	}

	public User selectQuanxian(Integer id) {
		User selectQuanxian = userMapper.selectQuanxian(id);
		return selectQuanxian;
	}

}
