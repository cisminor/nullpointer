package composite;

import bean.*;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import domain.Answer;
import domain.Question;
import edu.dragana.BeanLookup;
import util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnswerComposite extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private HorizontalLayout info_container;
	@AutoGenerated
	private Label label_user;
	@AutoGenerated
	private NativeButton nativeButton_downVote;
	@AutoGenerated
	private NativeButton nativeButton_upVote;
	@AutoGenerated
	private Label label_text;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Label label_downVotes;
	@AutoGenerated
	private Label label_upVotes;
	@AutoGenerated
	private Label label_1;

    private Answer answer;
    private Question question;
    private QuestionComposite parentComposite;
    private NativeButton nativeButton_accept;
    private Label label_accepted;
    private Label label_date;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public AnswerComposite(Answer answer, Question question, QuestionComposite composite) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
        this.answer = answer;
        this.question = question;
        parentComposite = composite;
        setData();
        addButtonListeners();
	}

    private void setData(){
        label_user.setValue(answer.getUser().toString());
        label_text.setValue(answer.getText());
        label_upVotes.setValue("+"+String.valueOf(answer.getUpVotes().size()));
        label_downVotes.setValue("-"+String.valueOf(answer.getDownVotes().size()));
        Date date = new Date(question.getDateCreated());
        String formatDate = new SimpleDateFormat("dd MMM yy HH:ss").format(date);
        label_date.setValue(formatDate);
        if (Util.getCurrentUser().equals(question.getUser())){
            if (question.getStatus() == Util.STATUS_OPEN){
                nativeButton_accept.setVisible(true);
            }
        } else {
            nativeButton_accept.setVisible(false);
        }
        if (answer.isAccepted()){
            label_accepted.setVisible(true);
        }

        try {
            UpVoteBeanLocal upBean = BeanLookup.get(UpVoteBean.class);

            if (upBean.findUpVoteByUser(Util.getCurrentUser(), answer) != null){
                nativeButton_upVote.setEnabled(false);
                nativeButton_upVote.setCaption("Up Voted");
                nativeButton_downVote.setEnabled(false);
            } else {
                DownVoteBeanLocal downBean = BeanLookup.get(DownVoteBean.class);
                if (downBean.findDownVoteByUser(answer, Util.getCurrentUser()) != null){
                    nativeButton_downVote.setEnabled(false);
                    nativeButton_downVote.setCaption("Down Voted");
                    nativeButton_upVote.setEnabled(false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Util.showError();
        }
    }

    private void addButtonListeners(){
        nativeButton_upVote.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    UpVoteBeanLocal upVoteBean = BeanLookup.get(UpVoteBean.class);
                    upVoteBean.insertUpVote(answer, Util.getCurrentUser());
                    updateAnswer();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }
            }
        });
        nativeButton_downVote.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    DownVoteBeanLocal downVoteBean = BeanLookup.get(DownVoteBean.class);
                    downVoteBean.insertDownVote(answer, Util.getCurrentUser());
                    updateAnswer();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }
            }
        });

        nativeButton_accept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    QuestionBeanLocal qBean = BeanLookup.get(QuestionBean.class);
                    answer.setAccepted(true);
                    question.setStatus(Util.STATUS_SOLVED);
                    qBean.updateQuestion(question);
                    parentComposite.updateQuestion();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }
            }
        });
    }

    private void updateAnswer(){
        try {
            AnswerBeanLocal answerBean = BeanLookup.get(AnswerBean.class);
            answer = answerBean.getAnswer(answer);
            setData();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Util.showError();
        }
    }

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("500px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("500px");
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
        verticalLayout_1.setStyleName("votes-stat");

        //label_accepted
        label_accepted = new Label();
        label_accepted.setStyleName("accepted");
        label_accepted.setValue("Accepted");
        label_accepted.setVisible(false);
        verticalLayout_1.addComponent(label_accepted);
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Votes");
        label_1.setStyleName("votes-label");
		verticalLayout_1.addComponent(label_1);
		verticalLayout_1.setComponentAlignment(label_1, new Alignment(20));
		
		// label_upVotes
		label_upVotes = new Label();
		label_upVotes.setImmediate(false);
		label_upVotes.setWidth("-1px");
		label_upVotes.setHeight("-1px");
		label_upVotes.setValue("+21");
        label_upVotes.setStyleName("votes-count");
		verticalLayout_1.addComponent(label_upVotes);
		verticalLayout_1
				.setComponentAlignment(label_upVotes, new Alignment(20));
		
		// label_downVotes
		label_downVotes = new Label();
		label_downVotes.setImmediate(false);
		label_downVotes.setWidth("-1px");
		label_downVotes.setHeight("-1px");
		label_downVotes.setValue("-12");
        label_downVotes.setStyleName("votes-count");
		verticalLayout_1.addComponent(label_downVotes);
		verticalLayout_1.setComponentAlignment(label_downVotes, new Alignment(
				20));
		
		return verticalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("500px");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
        verticalLayout_2.setStyleName("question-text-container");
		
		// label_text
		label_text = new Label();
		label_text.setImmediate(false);
		label_text.setWidth("500px");
		label_text.setHeight("-1px");
		label_text.setValue("Label");
        label_text.setContentMode(ContentMode.HTML);
		verticalLayout_2.addComponent(label_text);
		
		// info_container
		info_container = buildInfo_container();
		verticalLayout_2.addComponent(info_container);
		
		return verticalLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildInfo_container() {
		// common part: create layout
		info_container = new HorizontalLayout();
		info_container.setImmediate(false);
		info_container.setWidth("100.0%");
		info_container.setHeight("-1px");
		info_container.setMargin(false);
		
		// nativeButton_upVote
		nativeButton_upVote = new NativeButton();
		nativeButton_upVote.setCaption("Up Vote");
		nativeButton_upVote.setImmediate(false);
		nativeButton_upVote.setWidth("-1px");
		nativeButton_upVote.setHeight("-1px");
        nativeButton_upVote.setStyleName("vote-button");
		info_container.addComponent(nativeButton_upVote);
		
		// nativeButton_downVote
		nativeButton_downVote = new NativeButton();
		nativeButton_downVote.setCaption("Down Vote");
		nativeButton_downVote.setImmediate(false);
		nativeButton_downVote.setWidth("-1px");
		nativeButton_downVote.setHeight("-1px");
        nativeButton_downVote.setStyleName("vote-button");
		info_container.addComponent(nativeButton_downVote);

        //nativeButton_accept
        nativeButton_accept = new NativeButton();
        nativeButton_accept.setCaption("Accept");
        nativeButton_accept.setVisible(false);
        nativeButton_accept.setStyleName("accept-button");
        info_container.addComponent(nativeButton_accept);

        //label_date
        label_date = new Label();
        label_date.setStyleName("date-label");
        label_date.setWidth("-1px");
        label_date.setHeight("-1px");
        info_container.addComponent(label_date);
        info_container.setExpandRatio(label_date, 1.0f);
        info_container.setComponentAlignment(label_date, Alignment.MIDDLE_RIGHT);
		
		// label_user
		label_user = new Label();
		label_user.setImmediate(false);
		label_user.setWidth("-1px");
		label_user.setHeight("-1px");
		label_user.setValue("By ");
        label_user.setStyleName("user-label");
		info_container.addComponent(label_user);
//		info_container.setExpandRatio(label_user, 1.0f);
		info_container.setComponentAlignment(label_user, new Alignment(34));
		
		return info_container;
	}

}
