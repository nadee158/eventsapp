package com.janaka.projects.services.reports.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.janaka.projects.services.common.SecurityService;
import com.janaka.projects.services.reports.security.CustomAuthenticationFailureHandler;
import com.janaka.projects.services.reports.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityService securityService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
   //@formatter:off
    http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/assets/**","/ReportSerive/**","/CreateReport").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .failureUrl("/login?error")
      .defaultSuccessUrl("/home")
      .failureHandler(failureHandler())
      .loginPage("/login").passwordParameter("password").usernameParameter("username")
      .successHandler(successHandler())// autowired or defined below
      .permitAll()
      .and()
      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
      .permitAll();
   //@formatter:on
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    // SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    // handler.setUseReferer(true);
    return new CustomAuthenticationSuccessHandler();

  }

  @Bean
  public AuthenticationFailureHandler failureHandler() {
    // SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    // handler.setUseReferer(true);
    return new CustomAuthenticationFailureHandler();

  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(securityService);
    provider.setPasswordEncoder(this.passwordEncoder());
    return provider;
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityService);
    auth.authenticationProvider(daoAuthenticationProvider());
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



}
