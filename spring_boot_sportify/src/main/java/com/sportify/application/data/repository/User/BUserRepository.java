package com.sportify.application.data.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.User.BUser;

public interface BUserRepository extends JpaRepository<BUser, Long>{
    
}
