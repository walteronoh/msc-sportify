package com.sportify.application.data.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.event.Series;

public interface SeriesRepository extends JpaRepository<Series, Long> {

    
}

