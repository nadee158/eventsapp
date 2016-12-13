package com.janaka.projects.services.web.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.janaka.projects.services.common.SecurityService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;
import com.janaka.projects.services.web.security.RestAccessDeniedHandler;
import com.janaka.projects.services.web.security.RestUnauthorizedEntryPoint;
import com.janaka.projects.services.web.security.filters.StatelessAuthenticationFilter;
import com.janaka.projects.services.web.security.filters.StatelessLoginFilter;
import com.janaka.projects.services.web.security.utils.CustomAuthenticationProvider;
import com.janaka.projects.services.web.security.utils.TokenAuthenticationHandler;

@EnableWebSecurity
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource(name = "restUnauthorizedEntryPoint")
  private RestUnauthorizedEntryPoint restUnauthorizedEntryPoint;

  @Resource(name = "restAccessDeniedHandler")
  private RestAccessDeniedHandler restAccessDeniedHandler;

  @Autowired
  private TokenAuthenticationHandler tokenAuthenticationService;

  @Autowired
  private SecurityService securityService;

  public StatelessAuthenticationSecurityConfig() {
    super(true);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // @formatter:off
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .exceptionHandling().authenticationEntryPoint(restUnauthorizedEntryPoint)
        .accessDeniedHandler(restAccessDeniedHandler).and().anonymous().and().servletApi().and().headers().and()
        .authorizeRequests()
        // allow anonymous resource requests
        .antMatchers("/favicon.ico").permitAll().antMatchers("/css/**").permitAll().antMatchers("/js/**").permitAll()
        
        // allow anonymous POSTs to login
        .antMatchers("/" + ServiceEndpoints.SECURITY + ServiceEndpoints.SIGN_IN).permitAll()

        // allow anonymous POSTs to signup
        .antMatchers("/" + ServiceEndpoints.SECURITY + ServiceEndpoints.SIGN_UP).permitAll()

        // allow anonymous POSTs to reset passwrod
        .antMatchers("/" + ServiceEndpoints.SECURITY + ServiceEndpoints.RESET_PASSWORD).permitAll()
        
         // allow anonymous POSTs or gets to jmx
        .antMatchers("/jmx/**").permitAll()
        
        // allow anonymous POSTs or gets to captcha
        .antMatchers("/captcha/**").permitAll()
        
        // allow anonymous POSTs or gets to captcha
        .antMatchers("/managecontext/**").permitAll()

        // defined Admin only API area
        .antMatchers("/" + ServiceEndpoints.ADMIN_NAMESAPCE + "/**").hasRole("ADMIN")

        // all other request need to be authenticated
//        .anyRequest().authenticated().and()
        .anyRequest().permitAll().and() //permitted all requests!, to enable security, comment this line and uncomment above line

        // custom JSON based authentication by POST of {"username":"<name>","password":"<password>"}
        // which sets the token header upon authentication
        .addFilterBefore(new StatelessLoginFilter("/" + ServiceEndpoints.SECURITY + ServiceEndpoints.SIGN_IN,
            tokenAuthenticationService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

        // custom Token based authentication based on the header previously given to the client
        .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
            UsernamePasswordAuthenticationFilter.class);
    // @formatter:on



  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(securityService);
    auth.authenticationProvider(authenticationProvider());
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public CustomAuthenticationProvider authenticationProvider() {
    CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
    authenticationProvider.setSecurityService(securityService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }



}
