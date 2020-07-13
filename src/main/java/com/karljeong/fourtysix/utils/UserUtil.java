package com.karljeong.fourtysix.utils;

import java.math.BigInteger;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.karljeong.fourtysix.database.entity.TbComUser;

public class UserUtil {

	public static Authentication getAuthentication() {
		HttpServletRequest request = RequestContextUtil.getRequest();
		
		HttpSession session = request.getSession();
		SecurityContext sc = (SecurityContext) session
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

		if (sc == null) {
			return null;
		}

		return sc.getAuthentication();
		
	}
	public static TbComUser getUserInfo() {
		return (TbComUser) getAuthentication().getPrincipal();

	}
	
	public static HttpSession getSession() {
		HttpServletRequest request = RequestContextUtil.getRequest();
		return request.getSession();
	}

	public static BigInteger getUserId() {
		TbComUser tbComUser = getUserInfo();
		return tbComUser.getUserId();
	}
	
	public static boolean hasAuth(String checkAuth) {
		Authentication authentication = getAuthentication();
		
		Iterator<? extends GrantedAuthority> iter = authentication.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority gaIter = iter.next();
            String auth = gaIter.getAuthority();
            if (checkAuth.toUpperCase().equals(auth.toUpperCase())) {
            	return true;
            }
        }
        
        return false;
	}
}
