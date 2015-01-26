package bean;

import domain.DownVote;
import domain.Post;
import domain.Question;
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
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class DownVoteBean implements DownVoteBeanLocal {

    @PersistenceContext (name = "process-hibernate")
    EntityManager em;

    @Override
    public void insertDownVote(Post post, User user) {
        DownVote downVote = new DownVote();
        downVote.setPost(post);
        downVote.setUser(user);
        em.persist(downVote);
    }

    @Override
    public DownVote findDownVoteByUser(Post post, User user) {
        Query query = em.createNamedQuery("findDownVoteByUser");
        query.setParameter("user", user);
        query.setParameter("post", post);
        List<DownVote> list = query.getResultList();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
