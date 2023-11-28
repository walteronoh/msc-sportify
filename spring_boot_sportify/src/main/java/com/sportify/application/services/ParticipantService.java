package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.GameParticipant;
import com.sportify.application.data.entity.participant.Participant;
import com.sportify.application.data.repository.event.GameParticipantRepository;
import com.sportify.application.data.repository.participant.ParticipantRepository;

@Service
public class ParticipantService {
    public final ParticipantRepository participantRepository;
    public final GameParticipantRepository gameParticipantRepository;

    public ParticipantService(
        ParticipantRepository participantRepository,
        GameParticipantRepository gameParticipantRepository
        ) {
        this.participantRepository = participantRepository;
        this.gameParticipantRepository = gameParticipantRepository;
    }

    public void saveParticipant(Participant participant) {
        if (participant == null) {
            System.err.println("Participant is null.");
            return;
        }
        participantRepository.save(participant);
    }

    public void deleteParticipant(Participant participant) {
        participantRepository.delete(participant);
    }

    public List<Participant> findAllParticipants() {
        return participantRepository.findAll();
    }

    public List<Participant> findParticipant(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return participantRepository.findAll();
        } else {
            return participantRepository.search(filterText);
        }
    }
    public void saveGameParticipant(GameParticipant participant) {
        if (participant == null) {
            System.err.println("Game is null.");
            return;
        }
        gameParticipantRepository.save(participant);
    }

    public List<GameParticipant> findGameParticipant(String value) {
        return gameParticipantRepository.findByParticipant_NameContainingIgnoreCase(value);
    }

    public void deleteGameParticipant(GameParticipant participant) {
        gameParticipantRepository.delete(participant);
    }

    public List<GameParticipant> findGameParticipants(Game game) {
        return gameParticipantRepository.findByGame(game);
    }

    public List<Participant> getParticpants() {
        return participantRepository.findAll();
    }
}

