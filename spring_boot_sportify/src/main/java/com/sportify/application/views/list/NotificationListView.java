package com.sportify.application.views.list;

import com.sportify.application.services.NotificationService;
import com.sportify.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PageTitle("Notifications | Sportify")
@Route(value = "notifications", layout = MainLayout.class)
@PermitAll
public class NotificationListView extends VerticalLayout {
    NotificationService notificationService;

    public NotificationListView(NotificationService notificationService) {
        this.notificationService = notificationService;

        addClassName("list-view");
        setSizeFull();

        add(createPageView());
    }

    private Component createPageView() {
        HorizontalLayout horizontalLayout = new HorizontalLayout(createNotificationList());
        return horizontalLayout;
    }

    private VerticalLayout createNotificationList() {
        VerticalLayout verticalLayout = new VerticalLayout();
        notificationService.findAllNotifications().forEach(booking -> {
            Div descriptionDiv = new Div();
            descriptionDiv.setText("Description");

            VerticalLayout notificationLayout = new VerticalLayout();
            notificationLayout.add(descriptionDiv);
            notificationLayout.setWidth("50vw");

            notificationLayout.getStyle().set("border", "1px solid #ddd");
            notificationLayout.setPadding(true);

            verticalLayout.add(notificationLayout);

        });
        return verticalLayout;
    }
}
