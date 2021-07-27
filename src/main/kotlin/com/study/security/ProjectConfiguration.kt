package com.study.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class ProjectConfiguration : WebSecurityConfigurerAdapter() {

//    @Bean
//    fun userDetailService(): UserDetailsService {
//        var userDetailsService = InMemoryUserDetailsManager()
//        var user = User.withUsername("John")
//            .password("12345")
//            .authorities("read")
//            .build()
//
//        userDetailsService.createUser(user)
//        return userDetailsService
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return NoOpPasswordEncoder.getInstance()
//    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        var userDetailsService = InMemoryUserDetailsManager()
        var user = User.withUsername("John")
            .password("12345")
            .authorities("read")
            .build()

        userDetailsService.createUser(user)

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.httpBasic()
        httpSecurity.authorizeRequests()
            .anyRequest()
            .authenticated()
    }
}