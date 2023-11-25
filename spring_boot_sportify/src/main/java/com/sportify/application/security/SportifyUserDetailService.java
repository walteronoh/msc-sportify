package com.sportify.application.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.User.Promoter;
import com.sportify.application.data.repository.user.BUserRepository;
import com.sportify.application.data.repository.user.PromoterRepository;

public class SportifyUserDetailService implements UserDetailsService{
    @Autowired
    private BUserRepository userRepository;
    @Autowired
    private PromoterRepository promoterRepository;
    private Log log = LogFactory.getLog(SportifyUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BUser user = userRepository.findDistinctByName(username);
        Promoter promoter;
        if (user != null) {
            log.info(username + " found in BUser.");
            System.out.println(username + " found in BUser");
            return new SportifyUserDetails(user);
            // UserDetails userDet = User.builder()
            //         .username(user.get().getName())
            //         .passwordEncoder(p -> p)
            //         .password(user.get().getPassword())
            //         .roles("USER")
            //         .build();
            // return userDet;
        }
        else {
            promoter = promoterRepository.findDistinctByName(username);
            if (promoter != null) {
                log.trace(username + " found in Promoter.");
                System.out.println(username + " found in Promoter");
                return new SportifyUserDetails(promoter);
                // UserDetails promoterDet = User.builder()
                //         .username(promoter.get().getName())
                //         .passwordEncoder(p -> p)
                //         .password(promoter.get().getPassword())
                //         .roles("USER")
                //         .build();
                // return promoterDet;
            }
        }
        log.trace(username + " not found");
        System.out.println(username + " not found");
        throw new UsernameNotFoundException(username);
    }
    
}
