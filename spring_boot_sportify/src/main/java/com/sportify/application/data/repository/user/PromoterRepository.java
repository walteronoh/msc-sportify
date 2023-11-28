package com.sportify.application.data.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.User.Promoter;

public interface PromoterRepository extends JpaRepository<Promoter, Long>{

    public Promoter findDistinctByName(String username);
    
}
