package composite;

import bean.UserBean;
import bean.UserBeanLocal;
import com.vaadin.ui.*;
import domain.User;
import edu.dragana.BeanLookup;
import edu.dragana.MyVaadinApp;
import util.Util;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/25/14
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginComposite extends CustomComponent {

    private VerticalLayout mainLayout;
    private VerticalLayout loginContainer;
    private Label label_username;
    private TextField field_username;
    private Label validation;
    private Label label_password;
    private PasswordField field_password;
    private NativeButton login_button;

    public LoginComposite(){
        buildMainLayout();
        setCompositionRoot(mainLayout);
        addButtonListeners();
    }

    private void addButtonListeners(){
        login_button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String username = field_username.getValue();
                String password = field_password.getValue();
                if (username.equals("") || password.equals("")){
                    validation.setValue("Incorrect username/password");
                    return;
                }
                try {
                    UserBeanLocal userBean = BeanLookup.get(UserBean.class);
                    User user = userBean.getUserByUsernameAndPassword(username, password);
                    if (user != null){
                        UI.getCurrent().getSession().setAttribute("loggedin", user);
                        UI.getCurrent().getNavigator().navigateTo(MyVaadinApp.HOME_VIEW);
                    } else {
                        validation.setValue("Incorrect username/password");
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }
            }
        });
    }

    private void buildMainLayout(){
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");

        loginContainer = new VerticalLayout();
        loginContainer.setWidth("400px");
        loginContainer.setStyleName("login-container");
        mainLayout.addComponent(loginContainer);
        mainLayout.setComponentAlignment(loginContainer, Alignment.MIDDLE_CENTER);

        validation = new Label();
        validation.setStyleName("validation-error");
        loginContainer.addComponent(validation);

        label_username = new Label();
        label_username.setValue("Username");
        label_username.setStyleName("login-label");
        loginContainer.addComponent(label_username);

        field_username = new TextField();
        field_username.setStyleName("login-field");
        field_username.setWidth("300px");
        loginContainer.addComponent(field_username);



        label_password = new Label();
        label_password.setValue("Password");
        label_password.setStyleName("login-label");
        loginContainer.addComponent(label_password);

        field_password = new PasswordField();
        field_password.setStyleName("login-field");
        field_password.setWidth("300px");
        loginContainer.addComponent(field_password);

        login_button = new NativeButton();
        login_button.setCaption("Log in");
        login_button.setStyleName("login-button");
        loginContainer.addComponent(login_button);
    }

}
