package tk.lutsiuk.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
private DataSource dataSource;

@Override
protected void configure(HttpSecurity http) throws Exception {
	http
			.authorizeRequests()
			.antMatchers("/blog").hasAuthority("USER")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll();
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.usersByUsernameQuery("select email, password, is_active from user where email=?")
			.authoritiesByUsernameQuery("select u.email, ur.roles from user u inner join user_role ur on u.user_id  = ur.user_id where u.email=?");
}
}
