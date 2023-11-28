package com.sportify.application.views.list;

import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.services.VenueService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.VenueForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
    // we need a grid and a text field
    Grid<Venue> grid = new Grid<>(Venue.class);
    TextField filterText = new TextField();
    Button viewSections = new Button("View Venue Sections");

    VenueForm venueForm;

    VenueService venueService;

    Venue venue;

    public VenueListView(VenueService venueService) {
        this.venueService = venueService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();
        configureViewSection();

        add(
                getToolBar(),
                getContent());

        updateList();

        closeEditor();
    }

    private void configureViewSection() {
        viewSections.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
    }

    private void closeEditor() {
        this.venue = null;
        viewSections.setVisible(false);
        venueForm.setVenue(null);
        venueForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(venueService.findVenue(filterText.getValue()));
    }

    private Component getContent() {
        viewSections.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        viewSections.setVisible(false);
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, venueForm);
        horizontalLayout.add(viewSections);
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

    private void saveVenue(VenueForm.SaveEvent event) {
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
        grid.addColumn(v -> v.getLocation()).setHeader("Location");
        grid.addColumn(v -> v.getCapacity()).setHeader("Capacity");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> {
            Venue v = e.getValue();
            editVenue(v);
            viewSections.addClickListener(
                    // event -> UI.getCurrent().navigate(VenueSectionListView.class, e.getValue().getId()));
                    event -> UI.getCurrent().navigate(VenueSectionListView.class, v.getId()));
            viewSections.setVisible(true);
        });
    }

    private void editVenue(Venue venue) {
        if (venue == null) {
            closeEditor();
        } else {
            venueForm.setVenue(venue);
            venueForm.setVisible(true);
            addClassName("editing");
        }
    }

}
