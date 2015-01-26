package bean;

import domain.Question;
import domain.Tag;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/4/14
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface TagBeanLocal {
    public Tag getTag(Tag tag);
    public List<Tag> getAllTags();
    public List<Question> getQuestionsForTag(Tag tag);
    public BigInteger getMaxCount();
}
