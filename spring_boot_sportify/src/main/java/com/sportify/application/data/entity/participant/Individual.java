package com.sportify.application.data.entity.participant;

import java.time.LocalDate;

import com.sportify.application.data.entity.enums.Gender;
import com.sportify.application.data.entity.event.Sport;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public abstract class Individual extends Participant {
    
    @Enumerated(EnumType.STRING)
    protected Gender gender;
    protected LocalDate dateOfBirth;

    public Individual() {
        super();
    }
    public Individual(String name_,
                String desc,
                Sport sp,
                Gender g,
                LocalDate dob
                ) {
        super(name_, desc,sp);
        this.gender = g;
        this.dateOfBirth = dob;
    }

    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender g) {
        this.gender = g;
    }

    public LocalDate getDateOfBirth () {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate d) {
        this.dateOfBirth = d;
    }
    
    public int getAge() {
        var today = LocalDate.now();
        return this.dateOfBirth.getYear() - today.getYear();
    }
}
