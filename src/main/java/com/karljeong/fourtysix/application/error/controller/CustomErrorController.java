package com.karljeong.fourtysix.application.error.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @GetMapping
    public String viewError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        System.out.println("getRequestURI : "+request.getRequestURI());
        System.out.println("httpStatus : "+httpStatus.toString());
        System.out.println("code : "+ status.toString());
        System.out.println("msg : "+httpStatus.getReasonPhrase());
        switch (status.toString()) {
        case "400":
            return this.view400();
        case "403":
            return this.view403();
        case "404":
            return this.view404();
        case "500":
            return this.view500();
        case "505":
            return this.view505();
        default:
            return this.view500();
        }
    }

	@GetMapping("/400")
	public String view400() {
		return "/view/error/error-400";
	}

    @GetMapping("/403")
    public String view403() {
        return "/view/error/error-403";
    }

    @GetMapping("/404")
    public String view404() {
        return "/view/error/error-404";
    }

    @GetMapping("/500")
    public String view500() {
        return "/view/error/error-500";
    }

    @GetMapping("/505")
    public String view505() {
        return "/view/error/error-505";
    }

}
