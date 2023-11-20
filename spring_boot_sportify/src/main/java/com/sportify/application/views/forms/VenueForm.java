package com.sportify.application.views.forms;

import com.sportify.application.data.entity.venue.Venue;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class VenueForm extends FormLayout {
    Binder<Venue> binder = new BeanValidationBinder<>(Venue.class);
    TextField name = new TextField("Venue Name");   
    TextField description = new TextField("Description");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Venue venue;

    public VenueForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                name,
                description,
                createButtonLayout());
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
        binder.readBean(venue);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(null, venue)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(venue);
            fireEvent(new SaveEvent(this, venue));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class VenueFormEvent extends ComponentEvent<VenueForm> {
        private final Venue venue;

        protected VenueFormEvent(VenueForm source, Venue venue) {
            super(source, false);
            this.venue = venue;
        }

        public Venue getVenue() {
            return venue;
        }
    }

    public static class SaveEvent extends VenueFormEvent {
        SaveEvent(VenueForm source, Venue venue) {
            super(source, venue);
        }
    }

    public static class DeleteEvent extends VenueFormEvent {
        DeleteEvent(VenueForm source, Venue venue) {
            super(source, venue);
        }
    }

    public static class CloseEvent extends VenueFormEvent {
        CloseEvent(VenueForm source) {
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
