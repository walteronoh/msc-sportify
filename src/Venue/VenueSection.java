package Venue;
import java.util.ArrayList;
import java.util.List;

import Utils.CreateID;

public class VenueSection {
    private String id;
    private String name;
    private String description;
    private int capacity;
    private boolean vip;
    private Float seatPrice;
    private List<Integer> rows;

    public VenueSection(String n, 
                        String d, 
                        List<Integer> rows_, 
                        Float price,
                        boolean is_vip
                        ) {
        this.id = CreateID.createID();
        this.name = n;
        this.description = d;
        this.vip = is_vip;
        this.seatPrice = price;
        this.rows = rows_;
        int cap = 0;
        for (Integer seats : rows_) {
            cap += seats;
        }
        this.capacity = cap;
    }

    public VenueSection (String n,
                         String d,
                         Float price,
                         boolean is_vip
                        ) {
        this (n, d, new ArrayList<Integer>(), price, is_vip);
    }

    public String getId() {
        return this.id;
    }

    // Get Name method
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isVip() {
        return this.vip;
    }

    public void setVip(boolean is_vip) {
        this.vip = is_vip;
    }

    public Float getSeatPrice() {
        return this.seatPrice;
    }
    public void setSeatPrice(float price) {
        this.seatPrice = price;
    }

    public List<Integer> getRows() {
        return this.rows;
    }
    public void addRows(List <Integer> rs) {
        this.rows.addAll(rs);
        int cap = this.capacity;
        for (Integer seats : rs) {
            cap += seats;
        }
        this.capacity = cap;
    }
    public void addRow(int r) {
        this.rows.add(r);
        this.capacity += r;
    }
    public void setRows(List <Integer> rs) {
        this.rows = rs;
        int cap = 0;
        for (Integer seats : rs) {
            cap += seats;
        }
        this.capacity = cap;
    }
}
