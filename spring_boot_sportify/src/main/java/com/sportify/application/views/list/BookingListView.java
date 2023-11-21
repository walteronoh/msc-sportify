package com.sportify.application.views.list;

import com.sportify.application.services.BookingService;
import com.sportify.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Bookings | Sportify")
@Route(value = "bookings", layout = MainLayout.class)
@PermitAll
public class BookingListView extends VerticalLayout {
    BookingService bookingService;

    public BookingListView(BookingService bookingService) {
        this.bookingService = bookingService;

        addClassName("list-view");
        setSizeFull();

        add(createPageView());
    }

    private Component createPageView() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(createBookingList());
        return horizontalLayout;
    }

    private VerticalLayout createBookingList() {
        VerticalLayout verticalLayout = new VerticalLayout();
        bookingService.findAllBookings().forEach(booking -> {
            Div descriptionDiv = new Div();
            descriptionDiv.setText("Description");

            Button payButton = new Button("Pay");
            Button cancelButton = new Button("Cancel");

            HorizontalLayout actions = new HorizontalLayout(payButton, cancelButton);

            VerticalLayout bookingLayout = new VerticalLayout();
            bookingLayout.add(descriptionDiv, actions);
            bookingLayout.setWidth("50vw");

            bookingLayout.getStyle().set("border", "1px solid #ddd");
            bookingLayout.setPadding(true);

            verticalLayout.add(bookingLayout);

        });
        return verticalLayout;
    }
}
