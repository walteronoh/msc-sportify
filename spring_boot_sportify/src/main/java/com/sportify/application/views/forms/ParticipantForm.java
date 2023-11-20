package com.sportify.application.views.forms;

import com.sportify.application.data.entity.event.Sport;
import com.sportify.application.data.entity.participant.Participant;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ParticipantForm extends FormLayout {
    Binder<Participant> binder = new BeanValidationBinder<>(Participant.class);
    TextField name = new TextField("Participant Name");   
    TextField description = new TextField("Description");
    ComboBox<Sport> sport = new ComboBox<>("Sports");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Participant participant;

    public ParticipantForm(List<Sport> sports) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);
        sport.setItems(sports);
        sport.setItemLabelGenerator(Sport::getName);

        add(
                name,
                description,
                sport,
                createButtonLayout());
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
        binder.readBean(participant);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(null, participant)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(participant);
            fireEvent(new SaveEvent(this, participant));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ParticipantFormEvent extends ComponentEvent<ParticipantForm> {
        private final Participant participant;

        protected ParticipantFormEvent(ParticipantForm source, Participant participant) {
            super(source, false);
            this.participant = participant;
        }

        public Participant getParticipant() {
            return participant;
        }
    }

    public static class SaveEvent extends ParticipantFormEvent {
        SaveEvent(ParticipantForm source, Participant participant) {
            super(source, participant);
        }
    }

    public static class DeleteEvent extends ParticipantFormEvent {
        DeleteEvent(ParticipantForm source, Participant participant) {
            super(source, participant);
        }

    }

    public static class CloseEvent extends ParticipantFormEvent {
        CloseEvent(ParticipantForm source) {
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
