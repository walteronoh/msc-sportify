package com.sportify.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import com.sportify.application.services.user.SportifyUserDetailService;
import com.sportify.application.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
//    public SecurityConfig() {
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/h2-console/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/h2-console/**")).permitAll());
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService user() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();
        UserDetails promoter = User.builder()
                .username("promoter")
                .password(encoder.encode("promoter"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin, promoter);
    }

    // @Bean
    // public SportifyUserDetailService sportifyUserDetails(){
    //     return new SportifyUserDetailService();
    // }

    // @Bean
    // public static PasswordEncoder passwordEncoder() {
    //     return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    // }
}
