package com.karljeong.fourtysix.application.login.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.database.entity.TbComUser;
import com.karljeong.fourtysix.utils.UserUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

    final ServletContext servletContext;

    LoginController(ServletContext servletContext) {
        this.servletContext = servletContext;
	}

	@GetMapping
	public String viewMain(Model model) {
		return "/view/login/login";
	}

    @GetMapping("/success")
    public void redirectLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TbComUser tbComUser  = UserUtil.getUserInfo(request);
        response.sendRedirect(servletContext.getContextPath() + "/");
    }

}
