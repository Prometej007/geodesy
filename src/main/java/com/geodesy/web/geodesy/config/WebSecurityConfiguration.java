package com.geodesy.web.geodesy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

///**
// * Created by danul on 27.04.2017.
// */
@Configuration
@ComponentScan("com.geodesy.web")
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/loginprocess").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/loginprocess").permitAll()
                .usernameParameter("name")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/sign-in")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/loginpage").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth, EmployeeDetailService employeeDetailService, BCryptPasswordEncoder encoder) throws Exception {
////        auth.userDetailsService(employeeDetailService).passwordEncoder(encoder);
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(employeeDetailService);
////    }
//
//    /**
//     * Initializes authentication manager.
//     */
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    /**
//     * Sets Bcrypt encoder to be used in application.
//     *
//     * @return PAsswordEncoder instance.
//     */
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
