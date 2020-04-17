package com.ronaldfdg.jobsApp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, estatus from usuario where username=?")
		.authoritiesByUsernameQuery("select u.username, p.perfil from usuarioperfil up "
									+ "inner join usuario u on u.id = up.idUsuario "
									+ "inner join perfil p on p.id = up.idPerfil "
									+ "where username = ?");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers(
					 "/bootstrap/**",
					 "/images/**",
					 "/tinymce/**",
					 "/logos/**",
					 "/curriculumns/**").permitAll()
		.antMatchers(
					 "/",
					 "/users/",
					 "/users/signup",
					 "/search",
					 "/vacants/view/**").permitAll()
		.antMatchers(
					 "/vacants/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
		.antMatchers(
					 "/categories/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
		.antMatchers(
					 "/users/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers(
					 "/requests/index").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
		.antMatchers(
					 "/requests/delete/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
		
		.anyRequest().authenticated()
		
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/index", true).permitAll()
		
		.and().logout().deleteCookies("JSESSIONID");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
