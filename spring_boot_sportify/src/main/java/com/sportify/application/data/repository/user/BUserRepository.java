package com.sportify.application.data.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.User.BUser;

public interface BUserRepository extends JpaRepository<BUser, Long>{

    public BUser findDistinctByName(String name);

    public List<BUser> findByNameContainingIgnoreCase(String filterText);

    // @Query("select u from BUser u" +
    //         "where lower(u.name) like lower(concat('%', :searchTerm, '%')) ")
    // public List<BUser> search(@Param("searchTerm") String searchTerm);
    
}
