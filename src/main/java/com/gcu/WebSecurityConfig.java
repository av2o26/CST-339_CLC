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
			// This disables cross-site request forgery (CSRF) protection
			.csrf(csrf -> csrf.disable())
			// This method allows you to specify authorization rules for different request patterns
			// In this case, it's using a lambda expression to configure the authorization rules
			.authorizeHttpRequests(auth -> auth
				// This line allows unrestricted access to the root URL ("/"),
				// Any URL starting with /images/ and /service/ are accessible by anyone without authentication
				.requestMatchers("/", "/register", "/doRegister").permitAll()
				.requestMatchers("/userInfo").hasAnyRole("USER")
				// Any other request must be authenticated
				.anyRequest().authenticated())
			// This method configures the form-based login authentication
			.formLogin(form -> form
					// This defines the path for the login component
					.loginPage("/login")
					// This specifies the username input field's parameter name in the login form
					.usernameParameter("username")
					// This specifies the password input field's parameter name in the login form
					.passwordParameter("password")
					.permitAll()
					// This defines the default path taken on login success
					// The path is also allowed because login has succeeded
					.defaultSuccessUrl("/shop/display", true))
			.logout(logout -> logout
					// This sets the URL for logging out to "/logout")
					.logoutUrl("/logout")
					// The next two lines invalidates the HTTP session and clears the authentication state
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.permitAll()
					// The path taken on logout
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
