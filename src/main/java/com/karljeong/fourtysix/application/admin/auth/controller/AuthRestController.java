package com.karljeong.fourtysix.application.admin.auth.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import resulthandler.ResultDto;
import resulthandler.ResultDto.ResultCodeEnum;
import resulthandler.ResultSetter;

@RestController
@RequestMapping("/v1/api/admin/auth")
public class AuthRestController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
	AuthRestController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@GetMapping
	public ResultDto readList(@RequestParam(required = false) Map<String, Object> searchRequest,
			final Pageable pageable) {
	    return new ResultSetter(ResultCodeEnum.SUCCESS, null, authService.readList(searchRequest, pageable), null).getResultDto();
	}

    @PostMapping
    public ResultDto create(@RequestBody TbComAuth tbComAuth) {
        TbComAuth createdTbComAuth = authService.create(tbComAuth);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Saved Successfully", createdTbComAuth, "/admin/auth/viewupdate/" + createdTbComAuth.getAuthId()).getResultDto();
    }

    @PostMapping("/{authId}")
    public ResultDto update(@RequestBody TbComAuth tbComAuth,
            @PathVariable("authId") BigInteger authId) {
        TbComAuth updatedTbComAuth = authService.update(tbComAuth);
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Modified Successfully", updatedTbComAuth, "/admin/auth/viewupdate/" + updatedTbComAuth.getAuthId()).getResultDto();
    }

    @DeleteMapping("/{authId}")
    public ResultDto delete(@PathVariable("authId") BigInteger authId) {
        return new ResultSetter(ResultCodeEnum.SUCCESS_REDIRECT, "Deleted Successfully", authService.delete(authId), "/admin/auth/viewmain").getResultDto();
    }
}
