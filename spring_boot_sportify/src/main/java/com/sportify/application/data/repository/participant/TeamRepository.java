package com.sportify.application.data.repository.participant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.participant.Team;

public interface TeamRepository extends JpaRepository<Team,Long>{
    
}
