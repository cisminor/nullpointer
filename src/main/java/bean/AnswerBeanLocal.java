package bean;

import domain.Answer;
import domain.Question;
import domain.User;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface AnswerBeanLocal {

    public void insertAnswer(Answer answer);
    public Answer getAnswer(Answer answer);
}
