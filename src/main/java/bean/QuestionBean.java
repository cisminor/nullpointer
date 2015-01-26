package bean;

import domain.Question;
import domain.Tag;
import domain.User;
import util.Util;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class QuestionBean implements QuestionBeanLocal{

    @PersistenceContext(name = "process-hibernate")
    EntityManager em;

    @Override
    public void insertQuestion(Question question) {

        em.merge(question);
    }

    @Override
    public void updateQuestion(Question question) {
        em.merge(question);
    }

    @Override
    public List<Question> getQuestionsByUser(User user) {
        Query query = em.createNamedQuery("findQuestionsByUser");
        query.setParameter("user", user);
        List<Question> questions = query.getResultList();
        return questions;
    }

    @Override
    public Question getQuestion(Question question) {
        return em.find(Question.class, question.getPostId());
    }

    @Override
    public void deleteQuestion(Question question) {
        Question q = em.find(Question.class, question.getPostId());
        em.remove(q);
    }

    @Override
    public List<Question> getPopularQuestions() {
        Query query = em.createNamedQuery("findQuestionsOrderedByAnswersCount");
        List<Question> list = query.getResultList();
        return list;
    }
}
