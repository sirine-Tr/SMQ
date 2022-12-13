package com.example.SMQ.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/reclamation/**").permitAll()
                .antMatchers("/api/consequance/**").permitAll()
                .antMatchers("/api/risque/**").permitAll()
                .antMatchers("/api/actionRisque/**").permitAll()
                .antMatchers("/api/causes/**").permitAll()
                .antMatchers("/api/pieceJointe/**").permitAll()
                .antMatchers("/api/actionReclamation/**").permitAll()
                .anyRequest().authenticated();
    }
}