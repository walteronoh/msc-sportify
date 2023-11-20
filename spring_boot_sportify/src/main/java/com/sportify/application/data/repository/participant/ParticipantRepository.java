package com.sportify.application.data.repository.participant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.application.data.entity.participant.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("select p from Participant p " +
            "where lower(p.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Participant> search(@Param("searchTerm") String searchTerm);
}
