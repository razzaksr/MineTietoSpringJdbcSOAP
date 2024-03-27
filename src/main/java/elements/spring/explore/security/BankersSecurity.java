package elements.spring.explore.security;

import elements.spring.explore.jdbc.BankersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BankersSecurity {

    @Autowired
    BankersService service;

    AuthenticationManager authenticationManager;

//    @Bean
//    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // with random generated password
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.formLogin();

//        httpSecurity.authorizeRequests().antMatchers("/bankerspoint/*").permitAll();

//        httpSecurity.authorizeRequests().antMatchers("/kyc/retrieve",
//                "/kyc/account/*","/kyc/pancard/*",
//                "/kyc/perfect/*","/kyc/min/*",
//                "/kyc/aadhaar","/kyc/created/*").hasAnyAuthority("admin","manager");
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAuthority("admin");
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST).hasAuthority("admin");
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin");
//
//        httpSecurity.authorizeRequests().anyRequest().authenticated();

//        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.userDetailsService(service);
//        authenticationManager=builder.build();
//        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }
}