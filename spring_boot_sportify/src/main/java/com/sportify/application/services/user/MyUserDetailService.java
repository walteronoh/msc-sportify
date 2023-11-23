package com.sportify.application.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.User.Promoter;
import com.sportify.application.data.repository.user.BUserRepository;
import com.sportify.application.data.repository.user.PromoterRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
    @Autowired
    private BUserRepository userRepository;
    @Autowired
    private PromoterRepository promoterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BUser user = userRepository.findByUsername(username);
        Promoter promoter;
        if (user != null) {
            return new MyUserDetails(user);
        }
        else {
            promoter = promoterRepository.findByUsername(username);
            if (promoter != null) {
                return new PromoterDetails(promoter);
            }
        }
        throw new UsernameNotFoundException(username);
    }
    
}
