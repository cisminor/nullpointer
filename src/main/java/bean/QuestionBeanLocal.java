package bean;

import domain.Question;
import domain.Tag;
import domain.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface QuestionBeanLocal {

    public void insertQuestion(Question question);
    public void updateQuestion(Question question);
    public List<Question> getQuestionsByUser(User user);
    public Question getQuestion(Question question);
    public void deleteQuestion(Question question);
    public List<Question> getPopularQuestions();
}
