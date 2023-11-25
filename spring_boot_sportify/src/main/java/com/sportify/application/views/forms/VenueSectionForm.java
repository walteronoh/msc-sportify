package com.sportify.application.views.forms;

import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.entity.venue.VenueSection;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class VenueSectionForm extends FormLayout {
    Binder<VenueSection> binder = new BeanValidationBinder<>(VenueSection.class);
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    NumberField capacity = new NumberField("Venue Capacity");
    TextField sectionMode = new TextField("Section Mode, i.e VIP");
    NumberField seatPrice = new NumberField("Seat Price");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private VenueSection venueSection;

    private Venue venue;

    public VenueSectionForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                name,
                description,
                sectionMode,
                capacity,
                seatPrice,
                createButtonLayout());
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setVenueSection(VenueSection venueSection) {
        this.venueSection = venueSection;
        binder.readBean(venueSection);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(null, venueSection)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            venueSection.setVenue(this.venue);
            binder.writeBean(venueSection);
            fireEvent(new SaveEvent(this, venueSection));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class VenueSectionFormEvent extends ComponentEvent<VenueSectionForm> {
        private final VenueSection venueSection;

        protected VenueSectionFormEvent(VenueSectionForm source, VenueSection venueSection) {
            super(source, false);
            this.venueSection = venueSection;
        }

        public VenueSection getVenueSection() {
            return venueSection;
        }
    }

    public static class SaveEvent extends VenueSectionFormEvent {
        SaveEvent(VenueSectionForm source, VenueSection venueSection) {
            super(source, venueSection);
        }
    }

    public static class DeleteEvent extends VenueSectionFormEvent {
        DeleteEvent(VenueSectionForm source, VenueSection venueSection) {
            super(source, venueSection);
        }
    }

    public static class CloseEvent extends VenueSectionFormEvent {
        CloseEvent(VenueSectionForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
