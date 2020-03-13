package com.karljeong.fourtysix.application.admin.auth.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public TbComAuth create(@RequestBody TbComAuth tbComAuth) {
        return authService.create(tbComAuth);
    }

    @PostMapping("/{authId}")
    public TbComAuth update(@RequestBody TbComAuth tbComAuth,
            @PathVariable("authId") BigInteger authId) {
        return authService.update(tbComAuth);
    }

    @DeleteMapping("/{authId}")
    public int delete(@PathVariable("authId") BigInteger authId) {
        return authService.delete(authId);
    }
}
