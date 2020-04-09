package com.karljeong.fourtysix.application.login.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.utils.UserUtil;

@Controller
@RequestMapping
public class LoginController {

    private final ServletContext servletContext;

    @Autowired
    LoginController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping("/login")
    public String viewMain(Model model) {
        return "/view/login/login";
    }

    @GetMapping("/login/success")
    public void redirectLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TbComUser tbComUser = UserUtil.getUserInfo(request);
        response.sendRedirect(servletContext.getContextPath() + "/admin/menu/viewmain");
    }

    @GetMapping("/login/f")
    public String viewFMain(Model model) {
        return "/view/login/FacebookLogin";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (session != null
                && session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }

            session.invalidate();

        }

        response.sendRedirect(servletContext.getContextPath() + "/main");
    }

}