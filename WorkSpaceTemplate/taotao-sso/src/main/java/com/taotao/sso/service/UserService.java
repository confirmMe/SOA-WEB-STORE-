package com.taotao.sso.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
   TaotaoResult   CheckUser( String param,Integer type) throws Exception;
   TaotaoResult   CreateUser(TbUser tbUser);
   TaotaoResult   Login(String username,String password,HttpServletRequest request,HttpServletResponse response);
   TaotaoResult   FindUserByToken(String param) ;
   TaotaoResult   logout(String token);
}
