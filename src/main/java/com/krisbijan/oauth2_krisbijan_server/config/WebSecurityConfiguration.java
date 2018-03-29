package com.krisbijan.oauth2_krisbijan_server.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import com.krisbijan.oauth2_krisbijan_server.service.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterBefore(corsFilter(), SessionManagementFilter.class).formLogin()
				.loginProcessingUrl("/authentication").passwordParameter("password").usernameParameter("username").and()
				.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true).logoutUrl("/logout")
				.logoutSuccessUrl("/").and().csrf().disable().anonymous().disable().authorizeRequests()
				.antMatchers("/authentication").permitAll()
				.antMatchers("/oauth/token").permitAll()
				.antMatchers("/oauth/revoke-token").permitAll()
				.antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/user/*").access("hasRole('ROLE_USER')");
	}
}
