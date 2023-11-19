package com.sportify.application.views.forms;

import com.sportify.application.data.entity.event.Sport;
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

public class SportForm extends FormLayout {
    Binder<Sport> binder = new BeanValidationBinder<>(Sport.class);
    TextField name = new TextField("Name");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Sport sport;

    public SportForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                name,
                createButtonLayout());
    }

    public void setSport(Sport sport) {
        this.sport = sport;
        binder.readBean(sport);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, sport)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(sport);
            fireEvent(new SaveEvent(this, sport));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class SportFormEvent extends ComponentEvent<SportForm> {
        private final Sport sport;

        protected SportFormEvent(SportForm source, Sport sport) {
            super(source, false);
            this.sport = sport;
        }

        public Sport getSport() {
            return sport;
        }
    }

    public static class SaveEvent extends SportFormEvent {
        SaveEvent(SportForm source, Sport sport) {
            super(source, sport);
        }
    }

    public static class DeleteEvent extends SportFormEvent {
        DeleteEvent(SportForm source, Sport sport) {
            super(source, sport);
        }

    }

    public static class CloseEvent extends SportFormEvent {
        CloseEvent(SportForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
