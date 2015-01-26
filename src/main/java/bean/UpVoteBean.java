package bean;

import domain.Post;
import domain.Question;
import domain.UpVote;
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
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class UpVoteBean implements UpVoteBeanLocal {

    @PersistenceContext(name = "process-hibernate")
    EntityManager em;

    @Override
    public void insertUpVote(Post post, User user) {
        UpVote vote = new UpVote();
        vote.setPost(post);
        vote.setUser(user);
        em.persist(vote);
    }

    @Override
    public UpVote findUpVoteByUser(User user, Post post) {
        Query query = em.createNamedQuery("findUpVoteByUser");
        query.setParameter("user", user);
        query.setParameter("post", post);
        List<UpVote> list = query.getResultList();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
