package com.karljeong.fourtysix.config.security;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.karljeong.fourtysix.application.admin.menu.service.MenuService;
import com.karljeong.fourtysix.database.entity.TbComUser;

@Component
public class WebSecurityUrlChecker {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final MenuService menuService;

	@Autowired
	WebSecurityUrlChecker(MenuService menuService) {
		this.menuService = menuService;
	}

	public boolean check(HttpServletRequest req, Authentication authentication) {
	    if (!((authentication.getPrincipal()) instanceof TbComUser)) {
	        System.out.println("GetPrincipal is not TbComUser");
	        return false;
	    }

	    TbComUser tbComUser = (TbComUser) authentication.getPrincipal();
	    String reqUri[] = req.getRequestURI().split("/");
	    if (reqUri.length == 1) {
	        return false;
	    }

	    String accessingUri = reqUri[1];


	    Iterator<? extends GrantedAuthority> iter = authentication.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority gaIter = iter.next();
            String auth = gaIter.getAuthority();

        }


	    return true;
	}


}
