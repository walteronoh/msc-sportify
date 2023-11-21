package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.repository.booking.BookingRepository;

@Service
public class BookingService {
    public final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void saveBooking(Booking booking) {
        if (booking == null) {
            System.err.println("Booking is null.");
            return;
        }
        bookingRepository.save(booking);
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }
}
