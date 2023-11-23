package com.sportify.application.data.repository.participant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.participant.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{
    
}
