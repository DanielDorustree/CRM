package com.emsapp.security;

import java.util.Collections;

import com.emsapp.views.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

  private static class EMSInMemoryUserDetailsManager extends InMemoryUserDetailsManager {
    public EMSInMemoryUserDetailsManager() {
      createUser(new User("developer@dorustree.in",
              "{noop}password",
              Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
    }
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Authorize access to /images/ without authentication
    http.authorizeRequests().antMatchers("/images/**").permitAll();
    // Set default security policy that permits Vaadin internal requests and
    // denies all other
    super.configure(http);
    setLoginView(http, LoginView.class, "/logout");
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    return new EMSInMemoryUserDetailsManager();
  }
}
