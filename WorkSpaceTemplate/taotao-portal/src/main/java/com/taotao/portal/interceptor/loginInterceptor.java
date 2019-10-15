package com.taotao.portal.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;

public class loginInterceptor implements HandlerInterceptor{

	@Value("${FindByToken}")
	private String tokenUrl;
	@Value("${SSO_LOGIN_URL}")
	private String loginUrl;
	//在handler执行之前处理
	//返回值决定该handler是否执行，true放行，false拦截
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从cookie中得到token的值
		String cookie=CookieUtils.getCookieValue(request, "TTtoken");
		//调用sso的服务验证登录状态
		String json= HttpClientUtil.doPost(tokenUrl+cookie);
		TaotaoResult result=TaotaoResult.formatToPojo(json, TbUser.class);
		//如果已经登录
		System.out.println("拦截器执行了");
		if(result.getStatus()==200){
			return true;
		}else{
		//没有登录则跳转到登录界面
		//登陆成功后跳转回原先的页面
			response.sendRedirect(loginUrl+"?redirect=http://localhost:8082"+request.getRequestURI());
			return false;
		}
		
	}

	//handler执行之后返回ModelAndView之前
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	//在handler执行之后执行
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
