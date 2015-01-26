package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import composite.LoginComposite;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/25/14
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginView extends VerticalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        addComponent(new LoginComposite());
    }
}
