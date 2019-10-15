package com.taotao.sso.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.druid.util.StringUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
    @Autowired
	private UserService userService;
	
	@RequestMapping("/check")
	@ResponseBody
	public 	Object checkUser(String param,Integer type, String callback) throws Exception{
		//注册页面的用户名邮箱校验，支持JSONP
		param=new String(param.getBytes("iso8859-1"), "utf-8");
		//由于用户名可能含有中文，需要对get请求重新编码
		TaotaoResult result=null;
		if(StringUtils.isEmpty(param)){
			result= TaotaoResult.build(400, "ParamOrTypeError");
		}
		if(type == null){
			result= TaotaoResult.build(400, "ParamOrTypeError");
		}
		if(type!=1&&type!=2&&type!=3){
			result= TaotaoResult.build(400, "ParamOrTypeError");
		}
		//将错误信息包装成json格式
		if(result!=null){
			if(callback!=null){
				MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			}else{
				return result;
			}
		}
		
		try {
			result= userService.CheckUser(param, type);
		} catch (Exception e) {
			result= TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
			if(callback!=null){
				MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			}else{
				return result;
			}
		
		
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public TaotaoResult register(TbUser tbUser){
		try {
			System.out.println(tbUser.getUsername());
			userService.CreateUser(tbUser);
		return TaotaoResult.ok();
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	@RequestMapping("/login")
    @ResponseBody
    public TaotaoResult login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		try{
		return userService.Login(username, password,request,response);
		}catch(Exception e){
		return TaotaoResult.build(500, "服务器异常");
		}
	}
	@RequestMapping("/token/{param}")
	@ResponseBody 
	public Object FindUserByToken(@PathVariable String param,String callback){
		//根据token查询用户信息，支持JSONP调用
		TaotaoResult result=null;
		try {
			result= userService.FindUserByToken(param);
		} catch (Exception e) {
			result=TaotaoResult.build(500, "服务器异常");
		}
		if(callback!=null){
			System.out.println(callback);
			//如果此时是jsonp的调用方式，向控制台打印调用该方法的callback
			MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
		
			return jacksonValue;
		}else{
			return result;
		}
		
		
	
	}
	@RequestMapping("/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable String token,String callback){
		//安全退出，退出时删除redis中的key，支持JSONP调用
		TaotaoResult result=null;
		try {
			result=userService.logout(token);
		} catch (Exception e) {
			result=TaotaoResult.build(400, "error");
		}
		if(callback!=null){
			MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		}
		
		return result;
	}
	@RequestMapping("/logoutJsp/{token}")
	public String logoutJsp(@PathVariable String token,String callback){
		//安全退出，重定向到登录界面
		try{
			userService.logout(token);
		    return "login";
		}catch(Exception e){
			return "error";
		}
		
	}
	
}
