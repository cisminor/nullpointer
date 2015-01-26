package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import composite.MainLayout;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        MainLayout layout = new MainLayout();
        addComponent(layout);
        setComponentAlignment(layout, Alignment.TOP_CENTER);
        UI.getCurrent().getSession().setAttribute("layout", layout);


    }
}
