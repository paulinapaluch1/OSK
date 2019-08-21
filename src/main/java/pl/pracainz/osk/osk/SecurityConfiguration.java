package pl.pracainz.osk.osk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth
		.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
		.and()
		.withUser("kursant").password(passwordEncoder().encode("kursant")).roles("STUDENT");
	
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.httpBasic();

	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
