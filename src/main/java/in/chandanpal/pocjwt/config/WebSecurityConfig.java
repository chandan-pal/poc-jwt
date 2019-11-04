package in.chandanpal.pocjwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.chandanpal.pocjwt.filter.MyJwtFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
    private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
    private String rolesQuery;
	
	@Autowired
	private MyJwtFilter myJwtFilter;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Disable CSRF (cross site request forgery) because not using cookies
		httpSecurity.csrf().disable();
		
		//disable session because using JWT authorization
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//access denied page
		//httpSecurity.exceptionHandling().accessDeniedPage("/login");
		
		// Entry points
		httpSecurity.authorizeRequests()
				.antMatchers("/authenticate").permitAll()//permit api authentication to everyone
				.anyRequest().authenticated();// everything else to authenticated users
		
		// Apply JWT Filter
		httpSecurity.addFilterBefore(myJwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
