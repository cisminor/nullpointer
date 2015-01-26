package domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
        @NamedQuery(
                name = "findDownVoteByUser",
                query = "from DownVote d where d.user = :user and d.post = :post"
        )
})
@Entity
@Table (name = "down_vote")
public class DownVote {

    @Id
    @GeneratedValue
    @Column (name = "down_vote_id")
    private long downVoteId;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public DownVote() {
    }

    public long getDownVoteId() {
        return downVoteId;
    }

    public void setDownVoteId(long downVoteId) {
        this.downVoteId = downVoteId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
