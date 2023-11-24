package com.sportify.application.views.forms;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.Sport;
import com.sportify.application.data.entity.venue.Venue;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class EventForm extends FormLayout {
    Binder<Game> binder = new BeanValidationBinder<>(Game.class);
    TextField title = new TextField("Event Title");
    TextField description = new TextField("Description");
    DatePicker gameDate = new DatePicker("Event Date");
    ComboBox<Sport> sport = new ComboBox<>("Sports");
    ComboBox<Venue> venue = new ComboBox<>("Venue");
    MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
    Upload upload = new Upload(buffer);

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    private Game game;

    public EventForm(List<Sport> sports, List<Venue> venues) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);
        // Set sports comboBox
        sport.setItems(sports);
        sport.setItemLabelGenerator(Sport::getName);
        // Set venue comboBox
        venue.setItems(venues);
        venue.setItemLabelGenerator(Venue::getName);

        add(
                title,
                description,
                gameDate,
                sport,
                venue,
                // upload,
                createButtonLayout());
    }

    public void setGame(Game game) {
        this.game = game;
        binder.readBean(game);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(game);
            fireEvent(new SaveEvent(this, game));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class EventFormEvent extends ComponentEvent<EventForm> {
        private final Game game;

        protected EventFormEvent(EventForm source, Game game) {
            super(source, false);
            this.game = game;
        }

        public Game getGame() {
            return game;
        }
    }

    public static class SaveEvent extends EventFormEvent {
        SaveEvent(EventForm source, Game game) {
            super(source, game);
        }
    }

    public static class CloseEvent extends EventFormEvent {
        CloseEvent(EventForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
