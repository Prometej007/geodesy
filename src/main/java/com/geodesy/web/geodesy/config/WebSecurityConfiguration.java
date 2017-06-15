package com.geodesy.web.geodesy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

///**
// * Created by danul on 27.04.2017.
// */
@Configuration
@ComponentScan("com.geodesy.web")
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
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
