package com.ms.edu.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	private String REALM = "REAME";
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	};

	@Bean
	@Override
	public UserDetailsService userDetailsService(){
		UserBuilder users = User.builder();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		
		manager.createUser(users
				.username("schiavo.marco05@gmail.com")
				.password(new BCryptPasswordEncoder().encode("Pa55word"))
				.roles("USER")
				.build());
		
		/*manager.createUser(
				 users
				.username("Admin")
				.password(new BCryptPasswordEncoder().encode("VerySecretPwd"))
				.roles("USER","ADMIN")
				.build());*/
		
		return manager;
				
	}

	
	private static final String[] USER_MATCHER = {"/api/**"}; //autorizzazioni per questa API
	private static final String[] SIGNIN = {"/api/signin"}; //autorizzazioni per questa API
	
	public SecurityConfiguration(){}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.csrf().disable() //sistema di sicurezza che contrasta uno specifico attacco hacker nei service non mi interessa in questo caso lo disabilito
			.authorizeRequests()//metodo per gestire le autorizzazioni cioè in base al ruolo dell'utente vengono autorizzate determinate azioni che può compiere lo stesso
			//.antMatchers("/").permitAll().and().authorizeRequests()
			.antMatchers(SIGNIN).permitAll()
			.antMatchers(USER_MATCHER).hasAnyRole("USER")//l'utente con ruolo USER potrà accedere all'API USER_MATCHER posso aggiungere anche altri antMatchers
			.anyRequest().authenticated()
			.and() //cosa visualizzare nel caso in cui l'autenticazione non andrà a buon fine
			.httpBasic().realmName(REALM).authenticationEntryPoint(this.getBasicAuthEntryPoint())
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public AuthEntryPoint getBasicAuthEntryPoint(){
		return new AuthEntryPoint();
	}
	
	@Override
	/**
	 * metodo obbligatorio per poter lavorare con frontend come angular
	 */
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "**");
	}
	
	
	

}
