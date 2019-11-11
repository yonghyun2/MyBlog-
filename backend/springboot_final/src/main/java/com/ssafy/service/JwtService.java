package com.ssafy.service;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.vo.Member;

public interface JwtService {

	String makeJwt(Member member);
	String checkJwt(String jwt) throws Exception;
	
	
	String makeRefreshJwt(Member member);
	String checkRefreshJwt(String jwt) throws Exception;
	

}