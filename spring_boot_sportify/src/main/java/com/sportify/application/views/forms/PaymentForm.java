package com.sportify.application.views.forms;

import com.sportify.application.data.entity.booking.Booking;
import com.sportify.application.data.entity.enums.PaymentMethod;
import com.sportify.application.data.entity.payment.Payment;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class PaymentForm extends FormLayout {
    Binder<Payment> binder = new BeanValidationBinder<>(Payment.class);
    NumberField amount = new NumberField("Amount");
    ComboBox<PaymentMethod> paymentMethod = new ComboBox<>("Payment Method");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    private Booking booking;

    private Payment payment;

    public PaymentForm() {
        addClassName("contact-form");

        // Set payment method values
        paymentMethod.setItems(PaymentMethod.values());

        binder.bindInstanceFields(this);

        add(
                paymentMethod,
                amount,
                createButtonLayout());
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        binder.readBean(payment);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(null, payment)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save);
    }

    private void validateAndSave() {
        try {
            payment.setBooking(booking);
            binder.writeBean(payment);
            fireEvent(new SaveEvent(this, payment));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class PaymentFormEvent extends ComponentEvent<PaymentForm> {
        private final Payment payment;

        protected PaymentFormEvent(PaymentForm source, Payment payment) {
            super(source, false);
            this.payment = payment;
        }

        public Payment getPayment() {
            return payment;
        }
    }

    public static class SaveEvent extends PaymentFormEvent {
        SaveEvent(PaymentForm source, Payment payment) {
            super(source, payment);
        }
    }

    public static class DeleteEvent extends PaymentFormEvent {
        DeleteEvent(PaymentForm source, Payment payment) {
            super(source, payment);
        }
    }

    public static class CloseEvent extends PaymentFormEvent {
        CloseEvent(PaymentForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
