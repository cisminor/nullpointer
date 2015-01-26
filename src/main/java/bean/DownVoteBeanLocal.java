package bean;

import domain.DownVote;
import domain.Post;
import domain.Question;
import domain.User;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface DownVoteBeanLocal {

    public void insertDownVote(Post post, User user);
    public DownVote findDownVoteByUser(Post post, User user);
}
