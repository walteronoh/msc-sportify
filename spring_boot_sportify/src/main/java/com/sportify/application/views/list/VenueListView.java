package com.sportify.application.views.list;

import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.services.VenueService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.VenueForm;
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

@PageTitle("Venue | Sportify")
@Route(value = "venue", layout = MainLayout.class)
@PermitAll
public class VenueListView extends VerticalLayout {
    //we need a grid and a text field
    Grid<Venue> grid = new Grid<>(Venue.class);
    TextField filterText = new TextField();

    VenueForm venueForm;

    VenueService venueService;

    public VenueListView(VenueService venueService) {
        this.venueService = venueService;
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
        venueForm.setVenue(null);
        venueForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(venueService.findVenue(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, venueForm);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, venueForm);

        horizontalLayout.setClassName("content");
        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }
    private void configureForm() {
        venueForm = new VenueForm();
        venueForm.setWidth("25em");


        venueForm.addSaveListener(this::saveVenue);
        venueForm.addDeleteListener(this::deleteVenue);
        venueForm.addCloseListener(this::closeVenue);
    }

    private  void saveVenue(VenueForm.SaveEvent event) {
        venueService.saveVenue(event.getVenue());
        updateList();
        closeEditor();
    }

    private void deleteVenue(VenueForm.DeleteEvent event) {
        venueService.deleteVenue(event.getVenue());
        updateList();
        closeEditor();
    }

    private void closeVenue(VenueForm.CloseEvent event) {
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by venue name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addVenueButton = new Button("Add venue");
        addVenueButton.addClickListener(e -> addVenue());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addVenueButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void addVenue() {
        grid.asSingleSelect().clear();
        editVenue(new Venue());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        setSizeFull();
        grid.setColumns("name", "description");        

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editVenue(e.getValue()));
    }

    private void editVenue(Venue venue) {
        if(venue == null) {
            closeEditor();
        } else {
            venueForm.setVenue(venue);
            venueForm.setVisible(true);
            addClassName("editing");
        }
    }

}

