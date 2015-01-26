package bean;

import domain.Post;
import domain.Question;
import domain.UpVote;
import domain.User;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UpVoteBeanLocal {

    public void insertUpVote(Post post, User user);
    public UpVote findUpVoteByUser(User user, Post post);
}
