
package com.karljeong.fourtysix.application.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karljeong.fourtysix.application.login.service.LoginService;
import com.karljeong.fourtysix.config.security.WebSecurityProvider;
import com.karljeong.fourtysix.resulthandler.ResultDto;
import com.karljeong.fourtysix.resulthandler.ResultDto.ResultCodeEnum;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

@RestController
@RequestMapping("/v1/api/login")
public class LoginRestController {

    private final LoginService loginService;
    private final WebSecurityProvider webSecurityProvider;

    @Autowired
	LoginRestController(LoginService loginService, WebSecurityProvider webSecurityProvider) {
		this.loginService = loginService;
		this.webSecurityProvider = webSecurityProvider;
	}

	@PostMapping("/f")
	public ResultDto login(HttpServletRequest req, @RequestBody Map<String, Object> requestBody) {
		ResultDto resultDto = new ResultDto();
		String accessToken = (String) requestBody.get("access_token");
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_12);
		try {
			User faebookUser = facebookClient.fetchObject("me", User.class,
					Parameter.with("fields", "email, name, id, picture, locale"));
			if (faebookUser != null) {
				resultDto = webSecurityProvider.facebookAuthenticate(faebookUser, req);
			}
		} catch (FacebookException ex) {
			resultDto.setResultMsg("Failed access to Facebook!");
			resultDto.setResultCd(ResultCodeEnum.FAIL_REDIRECT_ALERT);
			resultDto.setLinkUrl("/login/f");
		}

		return resultDto;
	}

}
