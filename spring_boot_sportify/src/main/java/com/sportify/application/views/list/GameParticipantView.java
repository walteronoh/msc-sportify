package com.sportify.application.views.list;

/**
 * GameParticipantView
 */
import java.util.List;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.GameParticipant;
import com.sportify.application.data.entity.participant.Participant;
import com.sportify.application.services.EventsService;
import com.sportify.application.services.ParticipantService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.GameParticipantForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Game Participants | Sportify")
@Route(value = "participant-section", layout = MainLayout.class)
@PermitAll
public class GameParticipantView extends VerticalLayout implements HasUrlParameter<Long> {
    Grid<GameParticipant> grid = new Grid<>(GameParticipant.class);
    TextField filterText = new TextField();

    GameParticipantForm form;

    EventsService eventsService;
    ParticipantService participantService;

    Game game;

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        if (parameter > 0) {
            this.game = eventsService.findGameById(parameter);
            form.setGame(this.game);
            List<GameParticipant> gameParticipants = participantService.findGameParticipants(this.game);
            grid.setItems(gameParticipants);
        }
    }

    public GameParticipantView(EventsService eventsService,
                                ParticipantService participantService
                            ) {
        this.eventsService = eventsService;
        this.participantService = participantService;

        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolBar(),
                getContent());

        updateList();

        closeEditor();
    }

    private void closeEditor() {
        form.setParticipant(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(participantService.findGameParticipants(game));
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, form);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, form);

        horizontalLayout.setClassName("content");
        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }

    private void configureForm() {
        List<Participant> participants_ = participantService.getParticpants();
        form = new GameParticipantForm(participants_);

        form.setWidth("25em");

        form.addSaveListener(this::saveGameParticipant);
        form.addDeleteListener(this::deleteGameParticipant);
        form.addCloseListener(this::closeGameParticipant);
    }

    private void saveGameParticipant(GameParticipantForm.SaveEvent event) {
        participantService.saveGameParticipant(event.getGameParticipant());
        updateList();
        closeEditor();
    }

    private void deleteGameParticipant(GameParticipantForm.DeleteEvent event) {
        participantService.deleteGameParticipant(event.getGameParticipant());
        updateList();
        closeEditor();
    }

    private void closeGameParticipant(GameParticipantForm.CloseEvent event) {
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by game name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addVenueButton = new Button("Add venue section");
        addVenueButton.addClickListener(e -> addGameParticipant());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addVenueButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void addGameParticipant() {
        grid.asSingleSelect().clear();
        editGameParticipant(new GameParticipant());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        setSizeFull();
        // grid.setColumns("name", "description");
        // grid.addColumn(v -> v.getSectionMode()).setHeader("Section Mode");
        // grid.addColumn(v -> v.getCapacity()).setHeader("Capacity");
        // grid.addColumn(v -> v.getSeatPrice()).setHeader("Seat Price");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editGameParticipant(e.getValue()));
    }

    private void editGameParticipant(GameParticipant gameParticipant) {
        if (gameParticipant == null) {
            closeEditor();
        } else {
            form.setGameParticicpant(gameParticipant);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}

