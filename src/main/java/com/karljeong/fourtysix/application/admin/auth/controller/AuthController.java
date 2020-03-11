package com.karljeong.fourtysix.application.admin.auth.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.user.service.UserService;

@Controller
@RequestMapping("/admin/auth")
public class AuthController {

    final AuthService authService;
    final UserService userService;

	AuthController(AuthService authService, UserService userService) {
	    this.authService = authService;
	    this.userService = userService;
	}

	@GetMapping("/viewmain")
	public String viewMain() {
		return "/view/admin/auth/auth";
	}

	@GetMapping("/viewcreate")
	public String viewCreate(Model model) {
        model.addAttribute("allUserList", userService.findAll());
		return "/view/admin/auth/authC";
	}

	@GetMapping("/viewupdate/{authId}")
	public String viewUpdate(Model model, @PathVariable("authId") BigInteger authId) {
		return "/view/admin/auth/authU";
	}
}
