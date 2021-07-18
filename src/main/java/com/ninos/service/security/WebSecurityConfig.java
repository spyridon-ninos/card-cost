package com.ninos.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * configures the security at the service layer (perimeter security)
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/health");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder
                .inMemoryAuthentication()
                    .withUser("admin")
                    .password(passwordEncoder().encode("adminpass"))
                    .authorities("ADMIN")
                .and()
                    .withUser("user")
                    .password(passwordEncoder().encode("userpass"))
                    .authorities("USER");
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/v1/clearings").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/clearings/*").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/v1/clearings/*").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/v1/clearings/payment-cards-cost").hasAnyAuthority("ADMIN", "USER")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .defaultSuccessUrl("/swagger-ui.html", true)
                    .failureUrl("/login?error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
