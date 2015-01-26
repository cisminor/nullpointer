package util;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import composite.MainLayout;
import domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/2/14
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static final int STATUS_OPEN = 0;
    public static final int STATUS_SOLVED = 1;

    public static User getCurrentUser(){
        return (User) UI.getCurrent().getSession().getAttribute("loggedin");
    }

    public static MainLayout getLayout(){
        return (MainLayout) UI.getCurrent().getSession().getAttribute("layout");
    }

    public static String getStatus(int status){
        if (status == STATUS_OPEN)
            return "OPEN";
        if (status == STATUS_SOLVED)
            return "SOLVED";
        return "";
    }

    public static void showError(){
        Notification.show("Ooops!", "Something went wrong. Please try again later.", Notification.Type.ERROR_MESSAGE);
    }
}
