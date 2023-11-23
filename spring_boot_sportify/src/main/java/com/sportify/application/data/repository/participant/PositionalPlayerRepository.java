package com.sportify.application.data.repository.participant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.participant.PositionalPlayer;

public interface PositionalPlayerRepository extends JpaRepository<PositionalPlayer, Long>{
    
}
