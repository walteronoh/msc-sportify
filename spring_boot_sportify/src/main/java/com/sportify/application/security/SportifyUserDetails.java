package com.sportify.application.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.User.GenericUser;
import com.sportify.application.data.entity.User.Promoter;

public class SportifyUserDetails implements UserDetails {

    private GenericUser user;
    private GrantedAuthority role;
    private GrantedAuthority userAuth = new SimpleGrantedAuthority("ROLE_USER");
    private GrantedAuthority adminAuth = new SimpleGrantedAuthority("ROLE_ADMIN");

    public SportifyUserDetails(BUser user) {
        this.user = user;
        this.role = new SimpleGrantedAuthority("ROLE_BOOKING");
    }
    public SportifyUserDetails(Promoter user) {
        this.user = user;
        this.role = new SimpleGrantedAuthority("ROLE_PROMOTER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(role);
        authorities.add(userAuth);
        authorities.add(adminAuth);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public GenericUser getUser() {
        return user;
    }

}
