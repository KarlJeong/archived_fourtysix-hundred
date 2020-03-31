package com.karljeong.fourtysix.config.intercepter;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CommonIntercepter extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Common PRE BEGIN-----------------------------");
		logger.info(request.getRequestURI());

		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
            Iterator<? extends GrantedAuthority> iter = authentication.getAuthorities().iterator();
            while (iter.hasNext()) {
                GrantedAuthority gaIter = iter.next();
                String auth = gaIter.getAuthority();
                System.out.println(">>>>" + auth);
            }
		}
		if (session != null
				&& session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) {

		}

		logger.info("Common PRE END-----------------------------");
		return super.preHandle(request, response, handler);
	}

}
