package composite;

import bean.QuestionBean;
import bean.QuestionBeanLocal;
import bean.TagBean;
import bean.TagBeanLocal;
import com.ibm.icu.impl.duration.TimeUnit;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import domain.Question;
import domain.Tag;
import domain.User;
import edu.dragana.BeanLookup;
import edu.dragana.MyVaadinApp;
import util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 12/2/14
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class AskQuestionComposite extends CustomComponent {

    private VerticalLayout mainLayout;
    private Label titleLabel;
    private TextField titleField;
    private Label titleValidation;
    private Label textLabel;
    private RichTextArea textField;
    private Label textValidation;
    private Label tagsLabel;
    private TextField tagsField;
    private Label tagsValidation;
    private NativeButton askQuestionButton;

    public AskQuestionComposite() {
        buildMainLayout();
        setCompositionRoot(mainLayout);
        addButtonListeners();
    }

    private void addButtonListeners() {
        askQuestionButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String title = titleField.getValue();
                String text = textField.getValue();
                String tags = tagsField.getValue();
                if (!validateTitle(title) || !validateText(text) || !validateTags(tags))
                    return;
                List<Tag> tagList = makeTags(tags);
                try {
                    Question question = new Question();
                    question.setTitle(title);
                    question.setText(text);
                    question.setTags(tagList);
                    question.setUser(Util.getCurrentUser());
                    question.setStatus(Util.STATUS_OPEN);
                    question.setDateCreated(new Date().getTime());
                    QuestionBeanLocal bean = BeanLookup.get(QuestionBean.class);
                    bean.insertQuestion(question);
                    Notification.show("Question added", Notification.Type.HUMANIZED_MESSAGE);
                    clear();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }
            }
        });
    }

    private void clear(){
        titleField.setValue("");
        textField.setValue("");
        tagsField.setValue("");
    }

    private boolean validateTitle(String title){
        clearValidationLabels();
        if (title.equals("")){
            titleValidation.setValue("Title field cannot be empty");
            return false;
        }
        if (title.length() <10){
            titleValidation.setValue("Title has to be at least 10 characters long");
            return false;
        }
        return true;
    }

    private boolean validateText(String text){
        clearValidationLabels();
        if (text.equals("")){
            textValidation.setValue("Question field cannot be empty");
            return false;
        }
        if (text.length() <20){
            textValidation.setValue("Question has to be at least 20 characters long");
            return false;
        }
        return true;
    }

    private boolean validateTags(String tags){
        clearValidationLabels();
        if (tags.equals("")){
            tagsValidation.setValue("Tags field cannot be empty");
            return false;
        }
        String[] tagArray = tags.split(",");
        if (tagArray.length > 5){
            tagsValidation.setValue("Maximum 5 tags");
            return false;
        }
        return true;
    }

    private List<Tag> makeTags(String tags){
        String[] tagArray = tags.split(",");
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        for (String t : tagArray){
            String name = t.trim();
            Tag tag = new Tag();
            tag.setTagName(name);
            try {
                TagBeanLocal tagBean = BeanLookup.get(TagBean.class);
                Tag dbTag = tagBean.getTag(tag);
                if (dbTag != null){
                    tagList.add(dbTag);
                } else {
                    tagList.add(tag);
                }
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                Util.showError();
            }

        }
        return tagList;
    }

    private void clearValidationLabels(){
        titleValidation.setValue("");
        textValidation.setValue("");
        tagsValidation.setValue("");
    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();

        titleLabel = new Label("Title");
        titleLabel.setStyleName("ask-question-label");
        mainLayout.addComponent(titleLabel);

        titleField = new TextField();
        titleField.setInputPrompt("What's your question? Be specific.");
        titleField.setWidth("500px");
        mainLayout.addComponent(titleField);

        titleValidation = new Label();
        titleValidation.setStyleName("validation-error");
        mainLayout.addComponent(titleValidation);

        textLabel = new Label("Text");
        textLabel.setStyleName("ask-question-label");
        mainLayout.addComponent(textLabel);

        textField = new RichTextArea();
        textField.setImmediate(true);
        mainLayout.addComponent(textField);

        textValidation = new Label();
        textValidation.setStyleName("validation-error");
        mainLayout.addComponent(textValidation);

        tagsLabel = new Label("Tags");
        tagsLabel.setStyleName("ask-question-label");
        mainLayout.addComponent(tagsLabel);

        tagsField = new TextField();
        tagsField.setInputPrompt("At least one tag such as (java, spring, sql), max 5 tags");
        tagsField.setWidth("500px");
        mainLayout.addComponent(tagsField);

        tagsValidation = new Label();
        tagsValidation.setStyleName("validation-error");
        mainLayout.addComponent(tagsValidation);

        askQuestionButton = new NativeButton("Ask Question");
        askQuestionButton.setStyleName("ask-question-button");
        mainLayout.addComponent(askQuestionButton);


    }
}
