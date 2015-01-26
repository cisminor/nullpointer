package domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
        @NamedQuery(
                name = "findQuestionsByUser",
                query = "from Question q where q.user = :user"
        ),
        @NamedQuery(
                name = "findQuestionsOrderedByAnswersCount",
                query = "select q " +
                        "from Question q " +
                        "left join q.answers a " +
                        "group by q " +
                        "order by count(a) DESC"
        )
})
@Entity
@Table (name = "question")
@PrimaryKeyJoinColumn (name = "question_id")
public class Question extends Post implements Serializable{

    @Column (name = "title")
    private String title;

    @Column (name = "status")
    private int status;

    @OneToMany (mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers;

    @ManyToMany (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable (name = "tag_question",
                joinColumns = @JoinColumn (name = "question_id"),
                inverseJoinColumns = @JoinColumn (name = "tag_id"))
    private List<Tag> tags;

    public Question() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
