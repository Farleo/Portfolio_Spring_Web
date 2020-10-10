package tk.lutsiuk.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
@EnableWebSecurity
@RestController
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/blog/edit/**").hasAuthority("ADMIN")
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
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select email, password, is_active from user where email=?")
				.authoritiesByUsernameQuery("select u.email, ur.roles from user u inner join user_role ur on u.user_id  = ur.user_id where u.email=?");
	}
	
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Formatter<LocalDate> localDateFormatter() {
		return new Formatter<>() {
			@Override
			public LocalDate parse(String text, Locale locale) {
				return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
			}
			
			@Override
			public String print(LocalDate object, Locale locale) {
				return DateTimeFormatter.ISO_DATE.format(object);
			}
		};
	}
	
	
	@Bean
	public Formatter<LocalDateTime> localDateTimeFormatter() {
		return new Formatter<>() {
			@Override
			public LocalDateTime parse(String text, Locale locale) {
				return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
			}
			
			@Override
			public String print(LocalDateTime object, Locale locale) {
				return DateTimeFormatter.ISO_DATE_TIME.format(object);
			}
		};
	}
}
