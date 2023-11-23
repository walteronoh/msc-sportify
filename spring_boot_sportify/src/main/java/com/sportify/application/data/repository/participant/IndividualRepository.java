package com.sportify.application.data.repository.participant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.participant.Individual;

public interface IndividualRepository extends JpaRepository<Individual, Long>{
    
}
