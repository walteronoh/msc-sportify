package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.participant.Participant;
import com.sportify.application.data.repository.participant.ParticipantRepository;

@Service
public class ParticipantService {
    public final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
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
}

