package com.sportify.application.views.list;

import com.sportify.application.data.entity.User.BUser;
import com.sportify.application.services.UserService;
import com.sportify.application.views.MainLayout;
import com.sportify.application.views.forms.UserForm;
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

@PageTitle("Users | Sportify")
@Route(value = "users", layout = MainLayout.class)
@PermitAll
public class UserView extends VerticalLayout{
    Grid<BUser> grid = new Grid<>(BUser.class);
    TextField filterText = new TextField();
    
    UserForm userForm;

    UserService service;

    /**
     * @param service
     */
    public UserView(UserService service){
        this.service = service;
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
        userForm.setUser(null);
        userForm.setVisible(false);
        removeClassName("editing");

    }

    private void updateList() {
        grid.setItems(service.findUser(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, userForm);
        horizontalLayout.setFlexGrow(2, grid);
        horizontalLayout.setFlexGrow(1, userForm);

        horizontalLayout.setClassName("content");
        horizontalLayout.setSizeFull();

        return horizontalLayout;
    }

    private void configureForm() {
        userForm = new UserForm();
        userForm.setWidth("25em");

        userForm.addSaveListener(this::saveUser);
        userForm.addDeleteListener(this::deleteUser);
        userForm.addCloseListener(this::closeUser);
    }

    private void saveUser(UserForm.SaveEvent event) {
        service.saveUser(event.getUser());
        updateList();
        closeEditor();
    }

    private void deleteUser(UserForm.DeleteEvent event) {
        service.deleteUser(event.getUser());
        updateList();
        closeEditor();
    }

    private void closeUser(UserForm.CloseEvent event) {
        closeEditor();
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by user name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addUserButton = new Button("Add User");
        addUserButton.addClickListener(e -> addUser());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addUserButton);
        toolBar.addClassName("toolBar");
        return toolBar;
    }

    private void addUser() {
        grid.asSingleSelect().clear();
        editUSer(new BUser());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        setSizeFull();
        grid.setColumns("name", "email", "password");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editUSer(e.getValue()));
    }

    private void editUSer(BUser bUser) {
        if (bUser == null) {
            closeEditor();
        }
        else {
            userForm.setUser(bUser);
            userForm.setVisible(true);
            addClassName("editing");
        }
    }
}
