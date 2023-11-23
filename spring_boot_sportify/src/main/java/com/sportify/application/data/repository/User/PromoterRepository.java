package com.sportify.application.data.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.User.Promoter;

public interface PromoterRepository extends JpaRepository<Promoter, Long>{
    
}
