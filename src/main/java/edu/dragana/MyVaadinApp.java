package edu.dragana;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import domain.Question;
import domain.User;
import view.HomeView;
import view.LoginView;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/23/14
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Theme("mytheme")
public class MyVaadinApp extends UI {

    Navigator navigator;

    public static final String HOME_VIEW = "";
    public static final String LOGIN_VIEW = "login";

    @Override
    protected void init(VaadinRequest request) {

        navigator = new Navigator(this, this);
        navigator.addView(HOME_VIEW, new HomeView());
        navigator.addView(LOGIN_VIEW, new LoginView());

        if (getSession().getAttribute("loggedin") == null){
            navigator.navigateTo(LOGIN_VIEW);
        }
    }
}
