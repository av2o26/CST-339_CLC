package com.gcu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gcu.business.UsersBusinessService;

@Configuration
public class WebSecurityConfig 
{
	@Autowired
	UsersBusinessService service;
	
	// A bean is a managed object
	// Spring uses these types of factory methods when it needs the developer to provide configuration
	@Bean
	public SecurityFilterChain filterChaina(HttpSecurity http) throws Exception
	{
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/register", "/doRegister").permitAll()
				.requestMatchers("/userInfo").hasAnyRole("USER")
				.anyRequest().authenticated())
			.formLogin(form -> form
					.loginPage("/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.permitAll()
					.defaultSuccessUrl("/shop/display", true))
			.logout(logout -> logout
					.logoutUrl("/logout")
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.permitAll()
					.logoutSuccessUrl("/"));
		
		return http.build();
	}
		
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth
		.userDetailsService(service)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
