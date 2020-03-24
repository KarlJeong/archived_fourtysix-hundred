package com.karljeong.fourtysix.config.intercepter;

import java.util.Collection;
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

import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.database.entity.TbComUser;

@Component
public class PageAuthorizeIntercepter extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    final LoadStatic loadStatic;

    PageAuthorizeIntercepter(LoadStatic loadStatic) {
        this.loadStatic= loadStatic;
    }

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
        logger.info("Page PRE BEGIN-----------------------------");
        HttpSession session = request.getSession();

        if (session != null
                && session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {

                TbComUser tbComUser = (TbComUser) authentication.getPrincipal();
                setUserInfoSession(request, tbComUser);

                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

                if (!hasMenuList(request)) {
                    request.setAttribute("serviceMenuList", loadStatic.getServiceMenuList());

                    Iterator<? extends GrantedAuthority> iter = authorities.iterator();
                    while (iter.hasNext()) {
                        GrantedAuthority gaIter = iter.next();
                        String auth = gaIter.getAuthority();
                        if (auth != null && "ROLE_ADMIN".equals(auth)) {
                            request.setAttribute("systemMenuList", loadStatic.getSystemMenuList());
                        }
                    }
                }
            }

        }
        logger.info("Page PRE END-----------------------------");
        return super.preHandle(request, response, handler);
    }

    private void setUserInfoSession(HttpServletRequest request, TbComUser tbComUser) {
        request.setAttribute("userId", tbComUser.getUserId());
        request.setAttribute("loginId", tbComUser.getLoginId());
        request.setAttribute("userNickName", tbComUser.getUserNickname());
    }

    private Boolean hasMenuList(HttpServletRequest request) {
        if (request.getAttribute("systemMenuList") != null) {
            return true;
        }

        if (request.getAttribute("serviceMenuList") != null) {
            return true;
        }

        return false;
    }

}
