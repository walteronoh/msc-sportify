package com.sportify.application.data.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.event.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

    
}
