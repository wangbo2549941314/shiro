package com.ysd.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysd.service.UserServiece;

@Controller
public class test {
	@GetMapping("/test1")
	@ResponseBody 
	public String test1() {
		return "成功";
	}
	
	@GetMapping("/test2")
	public String test2(Model model) {
		model.addAttribute("name","成功转入页面");
		return "test";//跳转到test页面
	}
	
	@GetMapping("/add")
	public String add() {
		return "/user/add";
	}
	@GetMapping("/update")
	public String update() {
		return "/user/update";
	}
	@GetMapping("/tologin")
	public String tologin() {
		return "/login";
	}
	
	//登陆方法
	@RequestMapping("/login")
	public String login(String name,String password,Model model) {
		/**
		 * 使用shiro编写认证操作
		 */
		//获取subject
		Subject subject = SecurityUtils.getSubject();
		//封装用户数据（存放用户名，密码）
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		//执行登陆方法
		try {
			subject.login(token);
			//登陆成功:跳转到test.html
			return "redirect:/test2"; //redirect作用是跳转到一个请求
		} catch (UnknownAccountException e) {
			//登陆失败:用户名不存在
			model.addAttribute("msg","用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			//登陆失败:密码错误
			model.addAttribute("msg","密码错误");
			return "login";
		}
		
	}
	//授权页面
	@GetMapping("/unAuth")
	public String unAuth() {
		return "/Auth";
	}
	
}
