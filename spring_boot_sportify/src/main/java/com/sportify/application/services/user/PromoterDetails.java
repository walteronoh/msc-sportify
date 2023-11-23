package com.sportify.application.services.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sportify.application.data.entity.User.Promoter;

public class PromoterDetails implements UserDetails{

    private Promoter promoter;

    public PromoterDetails(Promoter p) {
        this.promoter = p;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority promoter = new SimpleGrantedAuthority("PROMOTER");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(promoter);
        return authorities;
        
    }

    @Override
    public String getPassword() {
        return promoter.getPassword();
    }

    @Override
    public String getUsername() {
        return promoter.getName();
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

}
