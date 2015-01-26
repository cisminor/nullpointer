package domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
        @NamedQuery(
                name = "findUpVoteByUser",
                query = "from UpVote u where u.user = :user and u.post = :post"
        )
})
@Entity
@Table (name = "up_vote")
public class UpVote {

    @Id
    @GeneratedValue
    @Column (name = "up_vote_id")
    private long upVoteId;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public UpVote() {
    }

    public long getUpVoteId() {
        return upVoteId;
    }

    public void setUpVoteId(long upVoteId) {
        this.upVoteId = upVoteId;
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
