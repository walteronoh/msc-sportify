package com.sportify.application.data.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.User.BUser;

public interface BUserRepository extends JpaRepository<BUser, Long>{

    BUser findByUsername(String username);
    
}
