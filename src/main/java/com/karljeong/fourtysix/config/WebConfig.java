package com.karljeong.fourtysix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.karljeong.fourtysix.config.intercepter.CommonIntercepter;
import com.karljeong.fourtysix.config.intercepter.PageAuthorizeIntercepter;
import com.karljeong.fourtysix.config.intercepter.RestApiAuthorizeIntercepter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CommonIntercepter commonInterceptor;
    private final PageAuthorizeIntercepter pageAuthorizeInterceptor;
    private final RestApiAuthorizeIntercepter restApiAuthorizeInterceptor;

    @Autowired
	WebConfig(CommonIntercepter commonInterceptor, PageAuthorizeIntercepter pageAuthorizeInterceptor,
			RestApiAuthorizeIntercepter restApiAuthorizeInterceptor) {
		this.commonInterceptor = commonInterceptor;
		this.pageAuthorizeInterceptor = pageAuthorizeInterceptor;
		this.restApiAuthorizeInterceptor = restApiAuthorizeInterceptor;

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(commonInterceptor).addPathPatterns("/", "/policy/**", "/main/**", "/admin/**", "/blog/**", "/notice/**", "/v1/api/**", "/b/**")
				.excludePathPatterns("/js/**, /vendors/**");

		registry.addInterceptor(pageAuthorizeInterceptor).addPathPatterns("/", "/policy/**", "/main/**", "/admin/**", "/blog/**", "/notice/**", "/b/**")
				.excludePathPatterns("/js/**, /vendors/**");

		registry.addInterceptor(restApiAuthorizeInterceptor).addPathPatterns("/v1/api/**")
				.excludePathPatterns("/v1/api/login/**", "/v1/api/signup/**", "/v1/api/logout/**");

		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
}
