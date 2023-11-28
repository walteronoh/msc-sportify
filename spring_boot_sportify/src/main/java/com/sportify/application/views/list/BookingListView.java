package com.sportify.application.views.list;

import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.payment.Payment;
import com.sportify.application.services.BookingService;
import com.sportify.application.services.payment.PaymentService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.PaymentForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
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
    PaymentService paymentService;

    PaymentForm paymentForm;

    public BookingListView(BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;

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
            descriptionDiv.setText(booking.getGame().getDescription());

            Button payButton = new Button("Pay");
            payButton.addClickListener(event -> showPaymentForm(booking));

            Button showTicket = new Button("View Ticket");
            showTicket.addClickListener(event -> showTicket(booking));

            Button cancelButton = new Button("Cancel");
            cancelButton.addClickListener(event -> showCancelBooking(booking));

            HorizontalLayout actions = new HorizontalLayout(
                    booking.isPaidInFull() ? showTicket : new HorizontalLayout(payButton, cancelButton),
                    getPaymentStatus(booking));

            VerticalLayout bookingLayout = new VerticalLayout();
            bookingLayout.add(descriptionDiv, actions);
            bookingLayout.setWidth("50vw");

            bookingLayout.getStyle().set("border", "1px solid #ddd");
            bookingLayout.setPadding(true);

            verticalLayout.add(bookingLayout);

        });
        return verticalLayout;
    }

    public Span getPaymentStatus(Booking booking) {
        Span statusSpan = new Span();
        if (booking.isPaidInFull()) {
            statusSpan.add("Paid");
            statusSpan.getElement().getThemeList().add("badge success");
            return statusSpan;
        }
        statusSpan.add("Balance: " + booking.getBalance());
        statusSpan.getElement().getThemeList().add("badge error");
        return statusSpan;
    }

    public void showPaymentForm(Booking booking) {
        paymentForm = new PaymentForm();
        paymentForm.setBooking(booking);
        paymentForm.setPayment(new Payment());
        paymentForm.setWidth("25em");
        paymentForm.addSaveListener(this::savePayment);
        Dialog dialog = new Dialog(paymentForm);
        Button closeButton = new Button(new Icon("lumo", "cross"),
                (e) -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.getHeader().add(closeButton);
        dialog.open();
    }

    private void savePayment(PaymentForm.SaveEvent event) {
        paymentService.savePayment(event.getPayment());
    }

    private void cancelBooking(Booking booking) {
        bookingService.deleteBooking(booking);
    }

    public void showTicket(Booking booking) {
        VerticalLayout verticalLayout = new VerticalLayout(new H1("Test"));

        Grid<Booking> grid = new Grid<>(Booking.class);
        grid.setItems(booking);
        grid.addClassName("contact-grid");
        grid.setColumns("totalReserved", "totalBill");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        setSizeFull();

        verticalLayout.setSpacing(true);
        verticalLayout.getStyle().set("border", "1px solid #ddd");
        verticalLayout.setPadding(true);

        verticalLayout.add(grid);

        Dialog dialog = new Dialog(verticalLayout);
        Button closeButton = new Button(new Icon("lumo", "cross"),
                (e) -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        dialog.setWidth("50%");

        dialog.getHeader().add(closeButton);
        dialog.open();
    }

    public void showCancelBooking(Booking booking) {
        Dialog dialog = new Dialog("Would you like to cancel the Booking?");
        Button closeButton = new Button(new Icon("lumo", "cross"),
                (e) -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.getHeader().add(closeButton);

        Button cancelButton = new Button("No", (e) -> dialog.close());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button saveButton = new Button("Yes");
        saveButton.addClickListener(event -> cancelBooking(booking));

        dialog.getFooter().add(new HorizontalLayout(saveButton, cancelButton));

        dialog.open();
    }
}
