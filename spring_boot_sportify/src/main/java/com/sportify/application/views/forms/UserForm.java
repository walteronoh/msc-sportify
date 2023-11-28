package com.sportify.application.views.forms;

import com.sportify.application.data.entity.User.BUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.shared.Registration;

public class UserForm extends FormLayout {
    private BUser user;
    private boolean enablePasswordValidation;

    Binder<BUser> binder = new BeanValidationBinder<>(BUser.class);
    TextField name = new TextField("Name");
    EmailField email = new EmailField("Email");
    PasswordField password = new PasswordField("Password");
    PasswordField confirmPasswordField = new PasswordField("Confirm Password");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public UserForm() {
        addClassName("contact-form");

        // binder.bindInstanceFields(this);
        binder.forField(name).asRequired().bind("name");
        binder.forField(email).asRequired().bind("email");
        binder.forField(password).asRequired().withValidator(this::passwordValidator).bind("password");
        add(
            name,
            email,
            password,
            confirmPasswordField,
            createButtonLayout()
        );
    }

    public void setUser(BUser user) {
        this.user = user;
        binder.readBean(user);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(e -> validateAndSave());
        delete.addClickListener(e -> fireEvent(new DeleteEvent(this, user)));
        cancel.addClickListener(e -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private ValidationResult passwordValidator(String pass, ValueContext ctx) {
        int passLength = 4;
        if(pass == null || pass.length() < passLength) {
            return ValidationResult.error("Password should be at least " + passLength + " characters long");
        }
        if(!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }
        String confirmation = confirmPasswordField.getValue();

        if(pass != null && pass.equals(confirmation)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }
    private void validateAndSave() {
        try{
            binder.writeBean(user);
            fireEvent(new SaveEvent(this, user));
        }
        catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class UserFormEvent extends ComponentEvent<UserForm> {
        private final BUser user;

        protected UserFormEvent(UserForm source, BUser user) {
            super(source, false);
            this.user = user;
        }

        public BUser getUser() {
            return user;
        }
    }

    public static class SaveEvent extends UserFormEvent {
        SaveEvent(UserForm source, BUser user) {
            super(source, user);
            source.confirmPasswordField.clear();
        }
    }

    public static class DeleteEvent extends UserFormEvent {
        DeleteEvent(UserForm source, BUser user) {
            super(source, user);
            source.confirmPasswordField.clear();
        }
    }

    public static class CloseEvent extends UserFormEvent {
        CloseEvent(UserForm source) {
            super(source, null);
            source.confirmPasswordField.clear();
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
