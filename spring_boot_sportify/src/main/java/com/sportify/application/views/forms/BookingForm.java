package com.sportify.application.views.forms;

import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.entity.venue.VenueSection;
import com.sportify.application.views.list.BookingListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class BookingForm extends FormLayout {
    Binder<Booking> binder = new BeanValidationBinder<>(Booking.class);

    NumberField totalBill = new NumberField("Total Bill");
    NumberField amountPaid = new NumberField("Amount Payable");
    NumberField totalReserved = new NumberField("Total Reserved Seats");
    ComboBox<VenueSection> venueSection = new ComboBox<>("Venue Section");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    private Booking booking;

    private Game game;

    public BookingForm() {
        totalBill.setEnabled(false);
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                venueSection, totalReserved, totalBill, amountPaid, createButtonLayout());

        // Add event listeners to combobox and NumberField
        venueSection.addValueChangeListener(event -> {
            calculateBill(event.getValue().getSeatPrice(), totalReserved.getValue());
        });

        totalReserved.addValueChangeListener(e -> {
            if (venueSection.getValue() != null) {
                calculateBill(venueSection.getValue().getSeatPrice(), e.getValue());
            }
        });

        setVenueSections();
    }

    public void calculateBill(double x, double y) {
        this.totalBill.setValue(x * y);
    }

    public void setVenueSections() {
        venueSection.setItemLabelGenerator(VenueSection::getSectionMode);
    }

    public void setGame(Game game) {
        venueSection.setItems(game.getVenueSections());
        this.game = game;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
        binder.readBean(booking);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        cancel.addClickListener(event -> fireEvent(new CloseBooking(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(booking);
            fireEvent(new SaveBooking(this, booking));
            // Redirect to Bookings
            // showDialog();
            // UI.getCurrent().navigate(BookingListView.class);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void showDialog() {
        Dialog dialog = new Dialog("Event successfully reserved. You will be redirected for payments");
        dialog.open();
    }

    // Events
    public static abstract class BookingFormEvent extends ComponentEvent<BookingForm> {
        private final Booking booking;

        protected BookingFormEvent(BookingForm source, Booking booking) {
            super(source, false);
            this.booking = booking;
        }

        public Booking getBooking() {
            return booking;
        }
    }

    public static class SaveBooking extends BookingFormEvent {
        SaveBooking(BookingForm source, Booking booking) {
            super(source, booking);
        }
    }

    public static class CloseBooking extends BookingFormEvent {
        CloseBooking(BookingForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveBooking> listener) {
        return addListener(SaveBooking.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseBooking> listener) {
        return addListener(CloseBooking.class, listener);
    }
}
