package com.karljeong.fourtysix.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebSecurityProvider webSecurityProvider;

    @Autowired
    WebSecurityConfig(WebSecurityProvider webSecurityProvider) {
        this.webSecurityProvider = webSecurityProvider;
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/vendors/**", "/v1/**");
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(webSecurityProvider);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//
				.antMatchers("/**")
				.permitAll();

		http.formLogin().loginPage("/login") // default
				.loginProcessingUrl("/loginsecurity") // Login Process URL, Same URL should be called in login page
				.failureUrl("/login?error") // default
				.defaultSuccessUrl("/login/success") //
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
