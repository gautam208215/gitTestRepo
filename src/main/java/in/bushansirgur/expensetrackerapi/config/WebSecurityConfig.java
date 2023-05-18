package in.bushansirgur.expensetrackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/loginUser", "/register").permitAll().antMatchers("/expenses")
				.authenticated();
		http.formLogin();
		http.httpBasic();
		return http.build();
	}

//	  @Bean
//	    public WebSecurityCustomizer webSecurityCustomizer() {
//	        return (web) -> web.ignoring().antMatchers("/login", "/register");
//	    }

}
