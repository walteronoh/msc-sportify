package com.sportify.application.data.entity.participant;

import java.time.LocalDate;

import com.sportify.application.data.entity.enums.Gender;
import com.sportify.application.data.entity.event.Sport;

import jakarta.persistence.Entity;

@Entity
public class PositionalPlayer extends Individual {
    private String position;

    public PositionalPlayer() {
        super();
    }
    public  PositionalPlayer(String name_,
                        String desc_,
                        Sport sp,
                        Gender g,
                        LocalDate dob,
                        String pos
                        ) {
        super(name_, desc_, sp, g, dob);
        this.position = pos;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

}
