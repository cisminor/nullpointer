package domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table (name = "answer")
@PrimaryKeyJoinColumn (name = "answer_id")
public class Answer extends Post {

    @Column (name = "accepted")
    private boolean accepted;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Question question;

    public Answer() {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
