package bean;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(name = "process-hibernate")
    EntityManager em;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        Query query = em.createNamedQuery("findUserByUsernameAndPassword");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List list = query.getResultList();
        if (list.size() == 0)
            return null;
        return (User) list.get(0);
    }
}
