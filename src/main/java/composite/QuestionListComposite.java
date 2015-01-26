package composite;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import domain.Question;
import domain.Tag;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class QuestionListComposite extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private Label label_user;
	@AutoGenerated
	private HorizontalLayout tag_container;
	@AutoGenerated
	private NativeButton title_button;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Label label_answers_number;
	@AutoGenerated
	private Label label_answers;

    private Question question;
    private MainLayout mainComposite;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public QuestionListComposite(Question question, MainLayout mainComposite) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
        this.question = question;
        this.mainComposite = mainComposite;
        setData();
        addButtonListeners();
	}

    private void setData(){
        title_button.setCaption(question.getTitle());
        label_user.setValue(question.getUser().getUsername());
        label_answers_number.setValue(""+question.getAnswers().size());
        List<Tag> tags = question.getTags();
        for (int i=0; i<tags.size(); i++){
            TagComposite tagComposite = new TagComposite(tags.get(i), mainComposite);
            tag_container.addComponent(tagComposite);
            if (i == tags.size()-1){
                tag_container.setExpandRatio(tagComposite, 1);
            } else {
                tag_container.setExpandRatio(tagComposite, 0);
            }
        }
    }

    private void addButtonListeners(){
        title_button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                MainLayout layout = Util.getLayout();
                layout.getContainer().removeAllComponents();
                layout.getContainer().addComponent(new QuestionComposite(question, mainComposite));
            }
        });
    }

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("600px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
        mainLayout.setStyleName("question-list-container");
		
		// top-level component properties
		setWidth("600px");
		setHeight("-1px");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		mainLayout.addComponent(verticalLayout_2);
		mainLayout.setExpandRatio(verticalLayout_2, 1.0f);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("-1px");
		verticalLayout_1.setHeight("-1px");
		verticalLayout_1.setMargin(false);
        verticalLayout_1.setStyleName("answer-stat");
		
		// label_answers
		label_answers = new Label();
        label_answers.setImmediate(false);
        label_answers.setWidth("-1px");
        label_answers.setHeight("-1px");
        label_answers.setValue("Answers");
        label_answers.setStyleName("answer-label");
		verticalLayout_1.addComponent(label_answers);
		verticalLayout_1.setComponentAlignment(label_answers, Alignment.MIDDLE_CENTER);
		
		// label_answers_number
		label_answers_number = new Label();
        label_answers_number.setImmediate(false);
        label_answers_number.setWidth("-1px");
        label_answers_number.setHeight("-1px");
        label_answers_number.setValue("25");
        label_answers_number.setStyleName("answer-count");
		verticalLayout_1.addComponent(label_answers_number);
		verticalLayout_1.setComponentAlignment(label_answers_number,
				Alignment.MIDDLE_CENTER);
		
		return verticalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
		
		// title_button
		title_button = new NativeButton();
		title_button.setCaption("Title");
		title_button.setImmediate(true);
		title_button.setWidth("-1px");
		title_button.setHeight("-1px");
        title_button.setStyleName(Reindeer.BUTTON_LINK);
        title_button.addStyleName("question-list-title");
		verticalLayout_2.addComponent(title_button);
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		verticalLayout_2.addComponent(horizontalLayout_2);
		
		return verticalLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("100.0%");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		
		// tag_container
		tag_container = new HorizontalLayout();
		tag_container.setImmediate(false);
		tag_container.setWidth("100.0%");
		tag_container.setHeight("-1px");
		tag_container.setMargin(false);
		horizontalLayout_2.addComponent(tag_container);
		horizontalLayout_2.setExpandRatio(tag_container, 1.0f);
		
		// label_user
		label_user = new Label();
		label_user.setImmediate(false);
		label_user.setWidth("-1px");
		label_user.setHeight("-1px");
		label_user.setValue("User");
        label_user.setStyleName("user-label");
		horizontalLayout_2.addComponent(label_user);
		horizontalLayout_2.setComponentAlignment(label_user, new Alignment(34));
		
		return horizontalLayout_2;
	}

}
