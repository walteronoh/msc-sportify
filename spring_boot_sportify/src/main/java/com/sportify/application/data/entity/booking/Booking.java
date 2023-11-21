package com.sportify.application.data.entity.booking;

import com.sportify.application.data.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Booking extends AbstractEntity {
    @NotEmpty
    private Boolean attended;
    @NotEmpty
    private Boolean cancelled;
    @NotEmpty
    private Boolean paidInFull;
    @NotEmpty
    private Boolean madeReservations;
    @NotEmpty
    private Float totalBill;

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Boolean getAttended() {
        return this.attended;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getCancelled() {
        return this.cancelled;
    }

    public void setPaidInFull(Boolean paidInFull) {
        this.paidInFull = paidInFull;
    }

    public Boolean getPaidInFull() {
        return this.paidInFull;
    }

    public void setMadeReservations(Boolean madeReservations) {
        this.madeReservations = madeReservations;
    }

    public Boolean getMadeReservations() {
        return this.madeReservations;
    }

    public void setTotalBill(Float totalBill) {
        this.totalBill = totalBill;
    }

    public Float getTotalBill() {
        return this.totalBill;
    }
}
