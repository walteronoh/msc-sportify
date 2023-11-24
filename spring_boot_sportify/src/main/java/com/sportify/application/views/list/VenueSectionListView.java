package com.sportify.application.views.list;

import java.util.List;

import com.sportify.application.data.entity.venue.Venue;
import com.sportify.application.data.entity.venue.VenueSection;
import com.sportify.application.services.VenueService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.VenueSectionForm;
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

@PageTitle("Venue Section | Sportify")
@Route(value = "venue-section", layout = MainLayout.class)
@PermitAll
public class VenueSectionListView extends VerticalLayout implements HasUrlParameter<Long> {
    Grid<VenueSection> grid = new Grid<>(VenueSection.class);
    TextField filterText = new TextField();

    VenueSectionForm form;

    VenueService venueService;

    Venue venue;

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        if (parameter > 0) {
            this.venue = venueService.findvenueById(parameter);
            form.setVenue(this.venue); 
            List<VenueSection> venueSections = venueService.findVenueSections(venueService.findvenueById(parameter));
            grid.setItems(venueSections);
        }
    }

    public VenueSectionListView(VenueService venueService) {
        this.venueService = venueService;
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
        form.setVenueSection(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(venueService.findVenueSection(filterText.getValue()));
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
        form = new VenueSectionForm();

        form.setWidth("25em");

        form.addSaveListener(this::saveVenueSection);
        form.addDeleteListener(this::deleteVenueSection);
        form.addCloseListener(this::closeVenueSection);
    }

    private void saveVenueSection(VenueSectionForm.SaveEvent event) {
        venueService.saveVenueSection(event.getVenueSection());
        updateList();
        closeEditor();
    }

    private void deleteVenueSection(VenueSectionForm.DeleteEvent event) {
        venueService.deleteVenueSection(event.getVenueSection());
        updateList();
        closeEditor();
    }

    private void closeVenueSection(VenueSectionForm.CloseEvent event) {
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by venue section name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addVenueButton = new Button("Add venue section");
        addVenueButton.addClickListener(e -> addVenueSection());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addVenueButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void addVenueSection() {
        grid.asSingleSelect().clear();
        editVenueSection(new VenueSection());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        setSizeFull();
        grid.setColumns("name", "description");
        grid.addColumn(v -> v.getSectionMode()).setHeader("Section Mode");
        grid.addColumn(v -> v.getCapacity()).setHeader("Capacity");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editVenueSection(e.getValue()));
    }

    private void editVenueSection(VenueSection venueSection) {
        if (venueSection == null) {
            closeEditor();
        } else {
            form.setVenueSection(venueSection);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
