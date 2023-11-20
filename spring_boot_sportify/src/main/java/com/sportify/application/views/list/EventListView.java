package com.sportify.application.views.list;

import com.sportify.application.services.EventsService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.EventForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Events | Sportify")
@Route(value = "events", layout = MainLayout.class)
@PermitAll
public class EventListView extends VerticalLayout {
    EventForm eventForm;
    EventsService eventsService;

    public EventListView(EventsService eventsService) {
        this.eventsService = eventsService;

        addClassName("list-view");
        setSizeFull();

        configureEventForm();

        add(createPageView());

        // Actions needed here
        // createEvent();

    }

    private void configureEventForm() {
        eventForm = new EventForm(eventsService.findAllSports());
        eventForm.setWidth("25em");
        eventForm.addSaveListener(this::saveGame);
        eventForm.addCloseListener(this::closeGame);
    }

    private void saveGame(EventForm.SaveEvent event) {
        eventsService.saveGame(event.getGame());
        // updateList();
        closeEditor();
    }

    private void closeGame(EventForm.CloseEvent event) {
        closeEditor();
    }

    private void closeEditor() {
        eventForm.setGame(null);
        eventForm.setVisible(false);
        removeClassName("editing");
    }

    private Component createPageView() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(createEventsList(), eventForm);
        return horizontalLayout;
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
            image.setWidth("50%");

            Div descriptionDiv = new Div();
            descriptionDiv.setText("Description" + game.getDescription());

            Button joinButton = new Button("Join");

            HorizontalLayout actions = new HorizontalLayout(joinButton);

            VerticalLayout gameLayout1 = new VerticalLayout();

            gameLayout1.add(image, descriptionDiv, actions);

            // gameLayout.setAlignItems(Alignment.CENTER);

            gameLayout1.setWidth("50vw");

            gameLayout1.setSpacing(true);
            gameLayout1.getStyle().set("border", "1px solid #ddd");
            gameLayout1.setPadding(true);

            gameLayout.add(gameLayout1);
        });
        return gameLayout;
    }

}
