package com.karljeong.fourtysix.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.karljeong.fourtysix.application.admin.menu.service.MenuService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebSecurityProvider webSecurityProvider;
    private final MenuService menuService;

    @Autowired
    WebSecurityConfig(WebSecurityProvider webSecurityProvider, MenuService menuService) {
        this.webSecurityProvider = webSecurityProvider;
        this.menuService = menuService;
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/vendors/**");
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(webSecurityProvider);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()//
		.antMatchers("/", "/login/**", "/error/**", "/favicon.ico").permitAll()
        .anyRequest().access("@webSecurityUrlChecker.check(request, authentication)");
		//.anyRequest().permitAll();

		http.exceptionHandling().accessDeniedPage("/error/403");

		http.formLogin().loginPage("/login") // default
				.loginProcessingUrl("/loginsecurity") // Login Process URL, Same URL should be called in login page
				.failureUrl("/login?error") // default
				.defaultSuccessUrl("/main") //
				.usernameParameter("loginId") //
				.passwordParameter("password") //
				.permitAll();

		http.logout().invalidateHttpSession(true) //
				.clearAuthentication(true) //
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // default
				.logoutSuccessUrl("/main") //
				.permitAll();

		http.sessionManagement().maximumSessions(1).expiredUrl("/login").and().invalidSessionUrl("/login");
	}

}
