package edu.dragana;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/28/14
 * Time: 7:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class BeanLookup {

    public static <T,R> R get(Class<? extends T> clazz) throws Exception {
        String prefix = "java:module/";
        String name = clazz.getSimpleName();
        R t = null;
        try {
            t = (R) new InitialContext().lookup(prefix + name);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (t == null) {
            throw new Exception("No bean found for name " + prefix + name);
        }
        return t;
    }
}
