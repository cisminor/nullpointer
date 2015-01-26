package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/30/14
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
@NamedQueries({
        @NamedQuery(
                name = "findTagByName",
                query = "from Tag t where t.tagName = :tagName"
        ),
        @NamedQuery(
                name = "findAllTags",
                query = "from Tag t"
        )
})
@Entity
@Table (name = "tag")
public class Tag implements Serializable{

    @Id
    @GeneratedValue
    @Column (name = "tag_id")
    private long tagId;

    @Column (name = "tag_name")
    private String tagName;

    @ManyToMany (mappedBy = "tags")
    private List<Question> questions;

    public Tag() {
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return tagName;
    }
}
