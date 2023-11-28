package com.sportify.application.data.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.GameParticipant;

@Repository
public interface GameParticipantRepository extends JpaRepository<GameParticipant, Long>{

    List<GameParticipant> findByGame(Game game);

    List<GameParticipant> findByParticipant_NameContainingIgnoreCase(String value);
    
}
