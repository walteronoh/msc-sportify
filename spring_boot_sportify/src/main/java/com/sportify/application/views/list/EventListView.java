package com.sportify.application.views.list;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.Sport;
import com.sportify.application.services.EventsService;
import com.sportify.application.services.VenueService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.EventForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Events | Sportify")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class EventListView extends VerticalLayout {
    EventForm eventForm;
    EventsService eventsService;
    VenueService venueService;

    public EventListView(EventsService eventsService, VenueService venueService) {
        this.eventsService = eventsService;
        this.venueService = venueService;

        addClassName("list-view");
        setSizeFull();

        configureEventForm();

        createPageView();

        // Actions needed here
        // createEvent();

    }

    private void configureEventForm() {
        eventForm = new EventForm(eventsService.findAllSports(), venueService.findAllVenues());
        Game game = new Game();
        eventForm.setGame(game);
        eventForm.setWidth("25em");
        eventForm.addSaveListener(this::saveGame);
        eventForm.addCloseListener(this::closeGame);
    }

    private void saveGame(EventForm.SaveEvent event) {
        eventsService.saveGame(event.getGame());
        // updateList();
        closeEditor();
        showDialog();
    }

    private void showDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Event was successfully created.");
        Button closeButton = new Button(new Icon("lumo", "cross"),
                (e) -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.getHeader().add(closeButton);
        dialog.open();
    }

    private void closeGame(EventForm.CloseEvent event) {
        closeEditor();
    }

    private void closeEditor() {
        eventForm.setGame(null);
        eventForm.setVisible(false);
        removeClassName("editing");
    }

    private void createPageView() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(createEventsList(), eventForm);
        add(horizontalLayout);
    }

    private void createEvent() {
        Button addEventButton = new Button("Add Event");
        addEventButton.getStyle()
                .set("position", "fixed")
                .set("right", "20px");

        add(addEventButton);
    }

    private VerticalLayout createEventsList() {
        VerticalLayout gameLayout = new VerticalLayout();
        eventsService.findAllGames().forEach(game -> {
            Image image = new Image(
                    "https://imgs.search.brave.com/8WMDWc_3SyGre_ptm2lfCXorV0XfOvCWgnZ2Yxboy1c/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by9z/cG9ydHMtdG9vbHNf/NTM4NzYtMTM4MDc3/LmpwZz9zaXplPTYy/NiZleHQ9anBn",
                    "Game Image");
            // image.setWidth("100px");
            // image.setWidth("50%");
            // Make the image responsive
            image.setMaxWidth("100%");
            image.setMaxHeight("100%");

            Div descriptionDiv = new Div();
            descriptionDiv.setText("Description" + game.getDescription() + "Date" + game.getGameDate() + "Sport "
                    + game.getSport().getName());

            Button joinButton = new Button("Attend");
            joinButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            joinButton.addClickListener(event -> {
                UI.getCurrent().navigate(ReservationListView.class, game.getId());
            });

            HorizontalLayout actions = new HorizontalLayout(joinButton);

            VerticalLayout gameLayout1 = new VerticalLayout();

            gameLayout1.add(image, descriptionDiv, createEventStats(), actions);

            // gameLayout.setAlignItems(Alignment.CENTER);

            gameLayout1.setWidth("100%");

            gameLayout1.setSpacing(true);
            gameLayout1.getStyle().set("border", "1px solid #ddd");
            gameLayout1.setPadding(true);

            gameLayout.add(gameLayout1);
        });
        return gameLayout;
    }

    private HorizontalLayout createEventStats() {
        Span capacity = new Span("Capacity: 100");
        capacity.getElement().getThemeList().add("badge contrast");

        Span remaining = new Span("Remaining: 20");
        remaining.getElement().getThemeList().add("badge");

        Span price = new Span("Ksh 100");
        price.getElement().getThemeList().add("badge success");

        HorizontalLayout horizontalLayout = new HorizontalLayout(capacity, remaining, price);
        return horizontalLayout;
    }

}
