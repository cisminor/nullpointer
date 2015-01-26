package bean;

import domain.User;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UserBeanLocal {

    public User getUserByUsernameAndPassword(String username, String password);
}
