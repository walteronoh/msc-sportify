package com.sportify.application.views.list;

import com.sportify.application.data.entity.participant.Participant;
import com.sportify.application.services.EventsService;
import com.sportify.application.services.ParticipantService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.ParticipantForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Participants | Sportify")
@Route(value = "participants", layout = MainLayout.class)
@PermitAll
public class ParticipantListView extends VerticalLayout {
    //we need a grid and a text field
    Grid<Participant> grid = new Grid<>(Participant.class);
    TextField filterText = new TextField();

    ParticipantForm participantForm;

    EventsService eventsService;
    ParticipantService participantService;

    public ParticipantListView(EventsService eventsService, ParticipantService participantService) {
        this.eventsService = eventsService;
        this.participantService = participantService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolBar(),
                getContent()
        );

        updateList();

        closeEditor();
    }

    private void closeEditor() {
        participantForm.setParticipant(null);
        participantForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(participantService.findParticipant(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, participantForm);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, participantForm);

        horizontalLayout.setClassName("content");
        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }
    private void configureForm() {
        participantForm = new ParticipantForm(eventsService.findAllSports());
        participantForm.setWidth("25em");


        participantForm.addSaveListener(this::saveParticipant);
        participantForm.addDeleteListener(this::deleteParticipant);
        participantForm.addCloseListener(this::closeParticipant);
    }

    private  void saveParticipant(ParticipantForm.SaveEvent event) {
        participantService.saveParticipant(event.getParticipant());
        updateList();
        closeEditor();
    }

    private void deleteParticipant(ParticipantForm.DeleteEvent event) {
        participantService.deleteParticipant(event.getParticipant());
        updateList();
        closeEditor();
    }

    private void closeParticipant(ParticipantForm.CloseEvent event) {
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by participant name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addParticipantButton = new Button("Add participant");
        addParticipantButton.addClickListener(e -> addParticipant());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addParticipantButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void addParticipant() {
        grid.asSingleSelect().clear();
        editParticipant(new Participant());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        setSizeFull();
        grid.setColumns("name", "description", "sport");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editParticipant(e.getValue()));
    }

    private void editParticipant(Participant participant) {
        if(participant == null) {
            closeEditor();
        } else {
            participantForm.setParticipant(participant);
            participantForm.setVisible(true);
            addClassName("editing");
        }
    }

}

