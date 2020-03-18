package com.karljeong.fourtysix.config.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RestApiAuthorizeIntercepter extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RestApiAuthorizeIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	logger.info("API PRE BEGIN-----------------------------");
	logger.info(request.getRequestURI());
	logger.info("API PRE END-----------------------------");
	return super.preHandle(request, response, handler);
    }
}
