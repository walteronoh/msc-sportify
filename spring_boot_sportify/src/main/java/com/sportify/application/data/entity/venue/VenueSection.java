package com.sportify.application.data.entity.venue;

import java.util.ArrayList;
import java.util.List;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.converters.RowListConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;

@Entity
public class VenueSection extends AbstractEntity {
    private String name;
    private String description;
    private int capacity = 0;
    private boolean vip = false;
    private Float seatPrice = 0f;
    @Convert(
            converter = RowListConverter.class
    )
    private List<Integer> rows = new ArrayList<>();
    public VenueSection () {}

    public VenueSection (String name_,
                        String desc,
                        Float price_)
    {
        this.name = name_;
        this.description = desc;
        this.seatPrice = price_;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setVip(boolean vip) {
        this.vip = vip;
    }
    public boolean getVip() {
        return vip;
    }
    public boolean isVip() {
        return vip;
    }
    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }
    public Float getSeatPrice() {
        return seatPrice;
    }
    public List<Integer> getRows() {
        return new ArrayList<>(rows);
    }
    public void setRows(List<Integer> rows) {
        this.rows = rows;
        int cap = 0;
        for (Integer seats : this.rows) {
            cap += seats;
        }
        this.capacity = cap;
    }

}
