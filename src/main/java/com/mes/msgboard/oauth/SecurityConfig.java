package com.mes.msgboard.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Constructor disables the default security settings
	 */
	public SecurityConfig() {
		super(true);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui.html").and().ignoring().antMatchers("/webjars/**").and().ignoring()
				.antMatchers("/swagger-resources/**").and().ignoring().antMatchers("/v2/api-docs/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.antMatcher("/**").authorizeRequests().anyRequest().authenticated()
		.and()
		.antMatcher("/user/register").anonymous()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/v1.0/categories").access("hasRole('ROLE_ADMIN')");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}