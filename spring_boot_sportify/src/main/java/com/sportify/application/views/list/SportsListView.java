package com.sportify.application.views.list;

import com.sportify.application.data.entity.Contact;
import com.sportify.application.data.entity.event.Sport;
import com.sportify.application.services.CrmService;
import com.sportify.application.services.EventsService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.ContactForm;
import com.sportify.application.views.forms.SportForm;
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

@PageTitle("Sports | Sportify")
@Route(value = "sports", layout = MainLayout.class)
@PermitAll
public class SportsListView extends VerticalLayout {
    //we need a grid and a text field
    Grid<Sport> grid = new Grid<>(Sport.class);
    TextField filterText = new TextField();

    SportForm sportForm;

    EventsService eventsService;
    public SportsListView(EventsService eventsService) {
        this.eventsService = eventsService;
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
        sportForm.setSport(null);
        sportForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(eventsService.findSport(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, sportForm);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, sportForm);

        horizontalLayout.setClassName("content");
        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }
    private void configureForm() {
        sportForm = new SportForm();
        sportForm.setWidth("25em");


        sportForm.addSaveListener(this::saveSport);
        sportForm.addDeleteListener(this::deleteSport);
        sportForm.addCloseListener(this::closeSport);
    }

    private  void saveSport(SportForm.SaveEvent event) {
        eventsService.saveSport(event.getSport());
        updateList();
        closeEditor();
    }

    private void deleteSport(SportForm.DeleteEvent event) {
        eventsService.deleteSport(event.getSport());
        updateList();
        closeEditor();
    }

    private void closeSport(SportForm.CloseEvent event) {
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by sport name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addSportButton = new Button("Add sport");
        addSportButton.addClickListener(e -> addSport());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addSportButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void addSport() {
        grid.asSingleSelect().clear();
        editSport(new Sport());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editSport(e.getValue()));
    }

    private void editSport(Sport sport) {
        if(sport == null) {
            closeEditor();
        } else {
            sportForm.setSport(sport);
            sportForm.setVisible(true);
            addClassName("editing");
        }
    }

}

