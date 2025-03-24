package com.investment.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.investment.config.securityconfig.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	
	
		@Autowired
		private JwtAuthFilter jwtAuthenticationFilter;
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		
		
//		@Bean
//		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		    http
//		        .csrf().disable()
//		        .authorizeHttpRequests(auth -> auth
//		            .anyRequest().permitAll()
//		        )
//		        .httpBasic().disable()
//		        .formLogin().disable();
//		    return http.build();
//		}
		

		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			System.err.println(">????????????????????????????");
			http
			.authorizeHttpRequests(auth-> auth
					.requestMatchers("/auth/**").permitAll()  
					.anyRequest().authenticated()  
					//.anyRequest().denyAll()
					)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensure stateless sessions
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before UsernamePasswordAuthenticationFilter
	        .csrf(csrf -> csrf.disable())  ;// Disable CSRF for token-based authentication
	        //.cors(cors -> cors.configurationSource(corsConfigurationSource()));  //CORS (Cross-Origin Resource Sharing) 

			return http.build();//SecurityFilterChain is interface
		}

		private CorsConfigurationSource corsConfigurationSource() {
			return null;
		}
		
		@Bean
		AuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
			authenticationProvider.setUserDetailsService(userDetailsService());
			//authenticationProvider.setPasswordEncoder(passwordEncoder());
			return authenticationProvider;
		}
		
		
		@Bean
		UserDetailsService userDetailsService() {
			System.err.println("Authentication userDetailsService!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
			return new CustomUserDetailsService();
		}
		
		
		@Bean
		AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
			return authConfig.getAuthenticationManager();
		}

}
