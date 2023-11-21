package com.sportify.application.data.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.application.data.entity.notification.Notice;

public interface NotificationRepository extends JpaRepository<Notice, Long> {

    
}
