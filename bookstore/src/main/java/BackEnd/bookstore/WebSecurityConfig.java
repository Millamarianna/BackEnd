package BackEnd.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import BackEnd.bookstore.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig  {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/css/**").permitAll() 
        	.anyRequest().authenticated()
        	.and()
        	.headers().frameOptions().disable()
        	.and()
      .formLogin()
          .defaultSuccessUrl("/books", true)
          .permitAll()
          .and()
      .logout()
          .permitAll()
          .and()
          .httpBasic();
        
        http.cors().and().csrf().disable();
          
      return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
   

}
