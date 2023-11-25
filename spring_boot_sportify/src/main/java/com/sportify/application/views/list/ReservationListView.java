package com.sportify.application.views.list;

import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.event.Game;
import com.sportify.application.services.BookingService;
import com.sportify.application.services.EventsService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.BookingForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Reserve | Sportify")
@Route(value = "reserve", layout = MainLayout.class)
@PermitAll
public class ReservationListView extends VerticalLayout implements HasUrlParameter<Long> {
    BookingService bookingService;
    EventsService eventsService;
    BookingForm bookingForm;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Long game_id) {
        if (game_id > 0) {
            // set Games
            bookingForm.setGame(eventsService.findGameById(game_id));
        } else {
            UI.getCurrent().navigate(EventListView.class);
        }
    }

    ReservationListView(BookingService bookingService, EventsService eventsService) {
        bookingForm = new BookingForm();
        this.bookingService = bookingService;
        this.eventsService = eventsService;

        addClassName("list-view");
        setSizeFull();

        configureBookingForm();

        add(
                createPageView());
    }

    private void configureBookingForm() {
        Booking booking = new Booking();
        bookingForm.setBooking(booking);
        bookingForm.setGame(eventsService.findGameById(Integer.toUnsignedLong(1301)));
        bookingForm.setWidth("25em");
        bookingForm.addSaveListener(this::saveBooking);
        bookingForm.addCloseListener(this::closeBooking);
    }

    private void saveBooking(BookingForm.SaveBooking event) {
        bookingService.saveBooking(event.getBooking());
        // updateList();
        closeEditor();
        showDialog();
    }

    private void showDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("A booking was successfully created.");
        Button closeButton = new Button(new Icon("lumo", "cross"),
                (e) -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.getHeader().add(closeButton);
        dialog.open();
    }

    private void closeBooking(BookingForm.CloseBooking event) {
        closeEditor();
    }

    private void closeEditor() {
        bookingForm.setBooking(null);
        removeClassName("editing");
    }

    private Component createPageView() {
        // HorizontalLayout horizontalLayout = new HorizontalLayout(createSeatingSectionList(), bookingForm);
        HorizontalLayout horizontalLayout = new HorizontalLayout(bookingForm);
        return horizontalLayout;
    }

    private VerticalLayout createSeatingSectionList() {
        VerticalLayout seatingSectionLayout = new VerticalLayout();
        return seatingSectionLayout;
    }
}
