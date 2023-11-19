package com.sportify.application.data.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.event.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

}
