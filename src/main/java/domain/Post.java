package domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table (name = "post")
@Inheritance (strategy = InheritanceType.JOINED)
public class Post {

    @Id
    @GeneratedValue
    @Column (name = "post_id")
    private long postId;

    @Column (name = "date_created")
    private long dateCreated;


    @Column (name = "text")
    @Type(type="text")
    private String text;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UpVote> upVotes;

    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DownVote> downVotes;

    public Post() {
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UpVote> getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(List<UpVote> upVotes) {
        this.upVotes = upVotes;
    }

    public List<DownVote> getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(List<DownVote> downVotes) {
        this.downVotes = downVotes;
    }
}
