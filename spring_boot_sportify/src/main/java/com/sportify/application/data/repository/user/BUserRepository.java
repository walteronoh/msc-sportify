package com.sportify.application.data.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.application.data.entity.User.BUser;

@Repository
public interface BUserRepository extends JpaRepository<BUser, Long>{

    BUser findByName(String name);
    
}
