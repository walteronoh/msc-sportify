package com.sportify.application.data.entity.venue;

import com.sportify.application.data.entity.AbstractEntity;

public class SeatingSection extends AbstractEntity {

    public int getCapacity() {
        return 0;
    }

    public int getBookedCapacity() {
        return 0;
    }

    public int getPrice() {
        return 0;
    }

    public boolean book(Integer fst, Integer snd) {
        return false;
    }

    public boolean unBook(Integer fst, Integer snd) {
        return false;
    }
    
}
