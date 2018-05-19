package com.campanhamarketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.campanhamarketing.service.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioRepositoryImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/usuario/incluir").access("hasRole('ADMIN')")
				.antMatchers("/usuario/consultar").access("hasRole('ADMIN')").antMatchers("/home").authenticated()
				.anyRequest().authenticated().and().formLogin().loginPage("/").defaultSuccessUrl("/home", true)
				.permitAll().and().logout().logoutSuccessUrl("/").logoutUrl("/logout").permitAll();

		http.exceptionHandling().accessDeniedPage("/acessoNegado");
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioRepositoryImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
	/*
	 * CRIPTOGRAFANDO A SENHA PARA TESTE public static void main(String[] args) {
	 * 
	 * System.out.println(new BCryptPasswordEncoder().encode("123456")); }
	 */

}
