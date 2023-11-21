package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.notification.Notice;
import com.sportify.application.data.repository.notification.NotificationRepository;

@Service
public class NotificationService {
    public final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

     public void saveNotification(Notice notice) {
        if (notice == null) {
            System.err.println("Notification is null.");
            return;
        }
        notificationRepository.save(notice);
    }

    public void deleteNotification(Notice notice) {
        notificationRepository.delete(notice);
    }

    public List<Notice> findAllNotifications() {
        return notificationRepository.findAll();
    }
}
