package com.karljeong.fourtysix.application.user.mypage.myarticle.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karljeong.fourtysix.application.admin.auth.service.AuthService;
import com.karljeong.fourtysix.application.admin.user.service.UserService;
import com.karljeong.fourtysix.common.loadstatic.LoadStatic;
import com.karljeong.fourtysix.utils.UserUtil;
import com.karljeong.fourtysix.utils.ValidationUtil;

@Controller
@RequestMapping("/myarticle")
public class MyArticleController {

    private final LoadStatic loadStatic;
    private final AuthService authService;
    
    @Autowired
    MyArticleController(LoadStatic loadStatic, AuthService authService) {
        this.loadStatic = loadStatic;
        this.authService = authService;
    }

    @SuppressWarnings("unchecked")
	@GetMapping("{userId}/viewmain")
    public String viewMain(Model model, @RequestParam(required = false) Map<String, Object> searchRequest, final Pageable pageable, @PathVariable("userId") BigInteger userId) {
        List<Map<String, Object>> articleNumberList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ARTICLE_NUMBER").get("code");
        if (!ValidationUtil.isValidPageSize(pageable.getPageSize(), articleNumberList)) {
            throw new RuntimeException("Invalid Paging Request.");
        }
        model.addAttribute("articleNumber", articleNumberList);
        
        List<Map<String, Object>> articleCodeList = (List<Map<String, Object>>) loadStatic.getSystemCode().get("ARTICLE_CODE").get("code");
        if (authService.findUserAuth(userId, BigInteger.valueOf(18)) == null) {
        	for (int i = 0 ; i < articleCodeList.size(); i++) {
        		Map<String, Object> sub = articleCodeList.get(i);
        		if ("BLOG".equals(sub.get("codeValue"))) {
        			articleCodeList.remove(i);
        		}
        	}
        }
        model.addAttribute("articleCodeList", articleCodeList);

        return "view/mypage/myarticle/myarticle";
    }

}
