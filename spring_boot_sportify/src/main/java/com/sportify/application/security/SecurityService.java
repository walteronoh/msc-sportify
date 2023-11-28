package com.sportify.application.security;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.security.AuthenticationContext;

@Component
public class SecurityService {
    private final AuthenticationContext authenticationContext;

    public SecurityService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public SportifyUserDetails getAuthenticatedUser() {
        // return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
        return authenticationContext.getAuthenticatedUser(SportifyUserDetails.class).get();
    }
    public void logOut() {
        authenticationContext.logout();
    }
}
