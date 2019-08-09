package hu.hwsw.airportapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery("SELECT username, pass, enabled FROM userlist where username=?")
//			.authoritiesByUsernameQuery("SELECT username, authority FROM authorities where username=?");
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(encoder);
		return daoAuthenticationProvider;
	}

	//az alábbi kóddal védjük le a weboldal egyes részeit
	//azaz egyes weboldalakat bizonyos ROLE_okhoz kötünk 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.httpBasic()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login**").permitAll() //mindenki elérheti
			.antMatchers(HttpMethod.GET, "/api/v1/flights/**").authenticated() //bejelentkezéshez kötött
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/**").authenticated()
			.antMatchers(HttpMethod.PUT, "/api/**").authenticated()
			.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN") //ADMIN joghoz kötött
			.anyRequest().denyAll(); //minden egyéb aloldal nem elérhető 
	}

}
