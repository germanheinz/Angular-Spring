package com.springbootangular.api.auth;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//Se encarga de dar autorizacion a nuestra app si el jwt es ok
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/clientes","/api/clientes/page/**", "/api/clientes/upload/img/**")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/api/clientes/{id}").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/clientes/upload").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/clientes/{id}").hasRole("ADMIN")
                .antMatchers("/api/clientes/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
