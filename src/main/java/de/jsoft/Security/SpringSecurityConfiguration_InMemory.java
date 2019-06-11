package de.jsoft.Security;

import de.jsoft.model.Users;
import de.jsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.ArrayList;
import java.util.ListIterator;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringSecurityConfiguration_InMemory extends WebSecurityConfigurerAdapter {


	@Autowired
	UserService userService;

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception
	{

		ArrayList<Users> memusers = (ArrayList<Users>) userService.getLusertomem().getAllUsers();

		for (ListIterator<Users> iter = memusers.listIterator(); iter.hasNext(); )
		{
			Users element = iter.next();

			Integer id = element.getId();
			String email = element.getEmail();
			String password = element.getPasswd();
			String role = element.getRole();
			String username = element.getUsername();


			// 1 - can call methods of element
			// 2 - can use iter.remove() to remove the current element from the list
			// 3 - can use iter.add(...) to insert a new element into the list
			//     between element and iter->next()
			// 4 - can use iter.set(0...) to replace the current element




		}


		//loaduserstomemory.getAllUsers();
		System.out.print("users from database");

	/*
		auth.inMemoryAuthentication().
			withUser("user").password("password")
				.roles("USER");
		auth.inMemoryAuthentication()
			.withUser("admin").password("password")
				.roles("USER", "ADMIN");
				*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.httpBasic()

				.realmName("User Registration System")
			.and()
			.authorizeRequests()
				.antMatchers("/login/login.html", "/template/home.html", "/").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}
