package com.karljeong.fourtysix.utils;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.karljeong.fourtysix.database.entity.TbComUser;

public class UserUtil {

	public static TbComUser getUserInfo() {
		HttpServletRequest request = RequestContextUtil.getRequest();
		
		HttpSession session = request.getSession();
		SecurityContext sc = (SecurityContext) session
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

		if (sc == null) {
			return null;
		}

		Authentication authentication = sc.getAuthentication();

		return (TbComUser) authentication.getPrincipal();

	}
	
	public static HttpSession getSession() {
		HttpServletRequest request = RequestContextUtil.getRequest();
		return request.getSession();
	}

	public static BigInteger getUserId() {
		TbComUser tbComUser = getUserInfo();
		return tbComUser.getUserId();
	}
}
