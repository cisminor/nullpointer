package composite;

import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuComponent extends CustomComponent {

    private VerticalLayout mainLayout;

    public MenuComponent() {
        buildMainLayout();
        setCompositionRoot(mainLayout);
    }

    private void buildMainLayout(){
        mainLayout = new VerticalLayout();
        NativeButton button = new NativeButton("Dugmence");
        button.setStyleName("red");

        mainLayout.addComponent(button);
    }

}
