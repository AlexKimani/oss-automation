package com.safaricom.hackathon.ossautomation.security;

import com.safaricom.hackathon.ossautomation.security.jwt.JwtConfigurer;
import com.safaricom.hackathon.ossautomation.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/films/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/films/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/films/**").permitAll()
                .antMatchers(HttpMethod.POST, "/films/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/films/**").permitAll()
                .antMatchers(HttpMethod.GET, "/video/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/video/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/video/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/video/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/video/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }

    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/actuator/**");
    }

}
