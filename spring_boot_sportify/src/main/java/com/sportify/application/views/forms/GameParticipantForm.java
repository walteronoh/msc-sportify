package com.sportify.application.views.forms;

import java.util.List;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.GameParticipant;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class GameParticipantForm extends FormLayout{
    Binder<GameParticipant> binder =  new BeanValidationBinder<>(GameParticipant.class);
    ComboBox<Participant> participant = new ComboBox<>("Participant");


    Button save = new Button("Save");
    Button delete =  new Button("Delete");
    Button cancel = new Button("Cancel");

    private GameParticipant gameParticipant;
    private Game game;

    // public GameParticipantForm(Game game, List<Participant> participants) {
    public GameParticipantForm(List<Participant> participants) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);
        participant.setItems(participants);
        participant.setItemLabelGenerator(Participant::getName);

        add(
            participant,
            createButtonLayout()
        );
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setParticipant(GameParticipant gameParticipant) {
        this.gameParticipant = gameParticipant;
        binder.readBean(gameParticipant);
    }
    public void setGameParticicpant(GameParticipant gameParticipant) {
        this.gameParticipant = gameParticipant;
        binder.readBean(gameParticipant);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(e -> validateAndSave());
        delete.addClickListener(e -> fireEvent(new DeleteEvent(this, gameParticipant)));
        cancel.addClickListener(e -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            gameParticipant.setGame(game);
            binder.writeBean(gameParticipant);
            fireEvent(new SaveEvent(this, gameParticipant));
        }
        catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class GameParticipantFormEvent extends ComponentEvent<GameParticipantForm> {
        private final GameParticipant gameParticipant;

        protected GameParticipantFormEvent(GameParticipantForm source, GameParticipant gameParticipant) {
            super(source, false);
            this.gameParticipant = gameParticipant;
        }

        public GameParticipant getGameParticipant() {
            return gameParticipant;
        }
    }

    public static class SaveEvent extends GameParticipantFormEvent {
        SaveEvent(GameParticipantForm source, GameParticipant gameParticipant) {
            super(source, gameParticipant);
        }
    }

    public static class DeleteEvent extends GameParticipantFormEvent {
        DeleteEvent(GameParticipantForm source, GameParticipant gameParticipant) {
            super(source, gameParticipant);
        }
    }

    public static class CloseEvent extends GameParticipantFormEvent {
        CloseEvent(GameParticipantForm source) {
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
