package com.sportify.application.data.entity.participant;

import java.time.LocalDate;

import com.sportify.application.data.entity.enums.Gender;
import com.sportify.application.data.entity.event.Sport;

import jakarta.persistence.Entity;

@Entity
public class Manager extends Individual{
    public Manager () {
        super();
    }
    public Manager(String name_,
                String desc_,
                Sport sp,
                Gender g,
                LocalDate dob) {
        super(name_, desc_, sp, g, dob);
    }
}
