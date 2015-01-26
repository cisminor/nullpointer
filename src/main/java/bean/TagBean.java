package bean;

import domain.Question;
import domain.Tag;
import org.hibernate.Hibernate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class TagBean implements TagBeanLocal {

    @PersistenceContext(name = "process-hibernate")
    EntityManager em;

    @Override
    public Tag getTag(Tag tag) {
        Query query = em.createNamedQuery("findTagByName");
        query.setParameter("tagName", tag.getTagName());
        List<Tag> tags = query.getResultList();
        if (tags.size() == 0)
            return null;
        return tags.get(0);
    }

    @Override
    public List<Tag> getAllTags() {
        Query query = em.createNamedQuery("findAllTags");
        List<Tag> list = query.getResultList();
        return list;
    }

    @Override
    public List<Question> getQuestionsForTag(Tag tag) {
        Tag t = em.find(Tag.class, tag.getTagId());
        Hibernate.initialize(t.getQuestions());
        return t.getQuestions();
    }

    @Override
    public BigInteger getMaxCount() {
        Query q = em.createNativeQuery(
                 "select MAX(counted)\n" +
                 "from (\n" +
                    "select count(*) as counted\n" +
                    "from tag_question\n" +
                    "group by question_id) as counts;"
                );
        return (BigInteger) q.getResultList().get(0);
    }
}
