package com.demo.donations.config;

import java.util.Arrays;

import com.demo.donations.security.AuthFilter;
import com.demo.donations.security.RestAccessDeniedHandler;
import com.demo.donations.service.ValidateTokenService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${defaultSrc}")
    private String defaultSrc;
    @Value("${scriptSrc}")
    private String scriptSrc;
    @Value("${connectSrc}")
    private String connectSrc;
    @Value("${imgSrc}")
    private String imgSrc;
    @Value("${styleSrc}")
    private String styleSrc;
    @Value("${fontSrc}")
    private String fontSrc;

    private ValidateTokenService validateToken;

    public SecurityConfig(ValidateTokenService validateTokenService) {
        this.validateToken = validateTokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().contentSecurityPolicy(defaultSrc + scriptSrc + connectSrc + imgSrc + styleSrc + fontSrc).and()
            .frameOptions().sameOrigin().and()
            .authorizeRequests()
            .antMatchers("/*.js").permitAll()
            .antMatchers("/*.css").permitAll()
            .antMatchers("/*.ico").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/register").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/validateToken").permitAll()
            .anyRequest().authenticated().and()
            .addFilter(new AuthFilter(authenticationManager(), validateToken))
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
            .csrf().disable().cors().configurationSource(corsConfiguration());
    }

    @Bean
    RestAccessDeniedHandler accessDeniedHandler() { return new RestAccessDeniedHandler(); }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","DELETE","PUT","PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("X-Servertime"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
