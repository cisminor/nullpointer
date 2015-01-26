package bean;

import domain.Answer;
import domain.Question;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class AnswerBean implements AnswerBeanLocal{

    @PersistenceContext (name = "process-hibernate")
    EntityManager em;

    @Override
    public void insertAnswer(Answer answer) {

        em.persist(answer);
    }

    @Override
    public Answer getAnswer(Answer answer) {
        return em.find(Answer.class, answer.getPostId());
    }
}
