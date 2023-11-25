package com.sportify.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.User.Promoter;
import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.repository.user.BUserRepository;
import com.sportify.application.data.repository.user.PromoterRepository;

@Service
public class UserService {
    public final BUserRepository userRepository;
    public final PromoterRepository promoterRepository;

    // public UserService(@Autowired BUserRepository ur, @Autowired PromoterRepository pr) {
    //     this.promoterRepository = pr;
    //     this.userRepository = ur;
    // }
    public UserService(BUserRepository ur,
                        PromoterRepository pr) {
        this.promoterRepository = pr;
        this.userRepository = ur;
    }

    public void saveUser(BUser u) {
        if (u != null) {
            userRepository.save(u);
        }
    }
    public void savePromoter(Promoter p) {
        if (promoterRepository != null) {
            promoterRepository.save(p);
        }
    }
    public void saveUserFlush(BUser u) {
        if (u != null) {
            userRepository.saveAndFlush(u);
        }
    }
    public void savePromoterFlush(Promoter p) {
        if (promoterRepository != null) {
            promoterRepository.saveAndFlush(p);
        }
    }
    public List<BUser> getAllUsers() {
        return this.userRepository.findAll();
    }
    public List<Promoter> getAllPromoters() {
        return this.promoterRepository.findAll();
    }
    public Optional<BUser> findBUserByID(Long id) {
        return userRepository.findById(id);
    }
    public Optional<Promoter> findPromoterById(Long id) {
        return promoterRepository.findById(id);
    }
    public void addGameToPromoter(Promoter p, Game g){
        p.addGame(g);
        promoterRepository.save(p);
    }

}
