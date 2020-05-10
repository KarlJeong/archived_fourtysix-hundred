package com.karljeong.fourtysix.config.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbComUser;

@Component
public class WebSecurityUrlChecker extends AntPathMatcher{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WebSecurityProvider webSecurityProvider;
    private final LoadStatic loadStatic;

	@Autowired
	WebSecurityUrlChecker(WebSecurityProvider webSecurityProvider, LoadStatic loadStatic) {
		this.webSecurityProvider = webSecurityProvider;
		this.loadStatic = loadStatic;
	}

	public boolean check(HttpServletRequest req, Authentication authentication) {
	    if (!((authentication.getPrincipal()) instanceof TbComUser)) {
	        System.out.println("GetPrincipal is not TbComUser");
	        authentication = webSecurityProvider.visitorAuthenticate(req).getAuthentication();
	    }

	    Iterator<? extends GrantedAuthority> iter = authentication.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority gaIter = iter.next();
            String auth = gaIter.getAuthority();

            Map<String, ArrayList<List<String>>> patternList = loadStatic.getPatterList();
            ArrayList<List<String>> patternListByAuth = patternList.get(auth);
            for (List<String> pattern : patternListByAuth) {
                if (match(pattern.get(0), req.getRequestURI())) {
                    switch (pattern.get(1).toUpperCase()) {
                    case "GET":
                        if ("GET".equals(req.getMethod().toUpperCase())) {
                            return true;
                        }
                        break;
                    case "PUT":
                        if ("PUT".equals(req.getMethod().toUpperCase())) {
                            return true;
                        }
                        break;
                    case "POST":
                        if ("POST".equals(req.getMethod().toUpperCase())) {
                            return true;
                        }
                        break;
                    case "DELETE":
                        if ("DELETE".equals(req.getMethod().toUpperCase())) {
                            return true;
                        }
                        break;
                    }
                }

            }
        }

	    return false;
	}

}
