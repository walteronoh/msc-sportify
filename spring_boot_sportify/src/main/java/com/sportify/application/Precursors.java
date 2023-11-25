package com.sportify.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.data.entity.User.Promoter;
import com.sportify.application.data.repository.user.BUserRepository;
import com.sportify.application.data.repository.user.PromoterRepository;
import com.sportify.application.services.UserService;

@Component
public class Precursors {
    private BUserRepository bUserRepository;
    private PromoterRepository promoterRepository;


    public Precursors() {}

    @Autowired
    public void setRepositories(BUserRepository br, PromoterRepository pr){
        this.bUserRepository = br;
        this.promoterRepository = pr;
    }
    public void execute() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String userPass = encoder.encode("user");
        String promoterPass = encoder.encode("promoter");
        String adminPass = encoder.encode("admin");
        
        BUser user = new BUser("user", "user@mail.com", userPass);
        Promoter promoter = new Promoter("promoter", "promoter@mail.com", promoterPass);
        BUser admin = new BUser("admin", "admin@mail.com", adminPass);

        UserService s = new UserService(bUserRepository, promoterRepository);
        s.savePromoterFlush(promoter);
        s.saveUserFlush(user);
        s.saveUserFlush(admin);
    }
}

