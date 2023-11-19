package com.sportify.application.data.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.application.data.entity.event.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
    @Query("select s from Sport s " +
            "where lower(s.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Sport> search(@Param("searchTerm") String searchTerm);
}
