package com.sportify.application.views;

import com.sportify.application.security.SecurityService;
import com.sportify.application.views.list.EventListView;
import com.sportify.application.views.list.SportsListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink routerLink1 = new RouterLink("Events", EventListView.class);
        routerLink1.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink routerLink2 = new RouterLink("Sports", SportsListView.class);
        routerLink2.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink routerLink3 = new RouterLink("Bookings", SportsListView.class);
        routerLink3.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink routerLink4 = new RouterLink("Participants", SportsListView.class);
        routerLink4.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink routerLink5 = new RouterLink("Venues", SportsListView.class);
        routerLink5.setHighlightCondition(HighlightConditions.sameLocation());

         RouterLink routerLink6 = new RouterLink("Notifications", SportsListView.class);
        routerLink6.setHighlightCondition(HighlightConditions.sameLocation());

        VerticalLayout verticalLayout = new VerticalLayout(routerLink1, routerLink2, routerLink3, routerLink4, routerLink5, routerLink6);

        addToDrawer(verticalLayout);
    }

    private void createHeader() {
        H1 logo = new H1("Sportify");
        logo.addClassNames("text-l", "m-m");

        Button button = new Button("Log out", event -> securityService.logOut());


        HorizontalLayout horizontalLayout = new HorizontalLayout(new DrawerToggle(), logo, button);

        horizontalLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        horizontalLayout.expand(logo);
        horizontalLayout.setWidthFull();
        horizontalLayout.addClassNames("py-0", "px-m");

        addToNavbar(horizontalLayout);
    }
}
