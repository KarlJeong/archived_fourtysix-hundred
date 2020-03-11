package com.karljeong.fourtysix.application.admin.auth.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.user.service.UserService;
import com.karljeong.fourtysix.database.entity.TbComAuth;

@RestController
@RequestMapping("/v1/api/admin/auth")
public class AuthRestController {

    final AuthService authService;
    final UserService userService;

	AuthRestController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@GetMapping
	public Page<TbComAuth> readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
		return authService.readList(searchRequest, pageable);
	}
}
