package composite;

import bean.QuestionBean;
import bean.QuestionBeanLocal;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import domain.Question;
import edu.dragana.BeanLookup;
import edu.dragana.MyVaadinApp;
import util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: demetreh
 * Date: 11/29/14
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainLayout extends CustomComponent {

    private VerticalLayout mainLayout;
    private HorizontalLayout menuContainer;
    private NativeButton myQuestions;
    private NativeButton topQuestions;
    private NativeButton tags;
    private NativeButton askQuestion;
    private VerticalLayout container;
    private VerticalLayout wrapper;
    private HorizontalLayout header;
    private Label nil;
    private Label pointer;
    private Label username;
    private NativeButton logout_button;

    public MainLayout() {
        buildMainLayout();
        setCompositionRoot(wrapper);
        username.setValue(Util.getCurrentUser().getUsername());
        addButtonListeners();
        topQuestions.click();
    }

    private void addButtonListeners(){
        myQuestions.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                container.removeAllComponents();
                setActive((NativeButton) event.getButton());
                try {
                    QuestionBeanLocal questionBean = BeanLookup.get(QuestionBean.class);
                    ArrayList<Question> questions = (ArrayList<Question>) questionBean.getQuestionsByUser(Util.getCurrentUser());
                    for (Question q : questions){
                        container.addComponent(new QuestionListComposite(q, MainLayout.this));
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }
            }
        });
        topQuestions.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                container.removeAllComponents();
                setActive((NativeButton) event.getButton());
                try {
                    QuestionBeanLocal qBean = BeanLookup.get(QuestionBean.class);
                    List<Question> questions = qBean.getPopularQuestions();
                    for (Question q : questions){
                        container.addComponent(new QuestionListComposite(q, MainLayout.this));
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Util.showError();
                }

            }
        });
        tags.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                container.removeAllComponents();
                setActive((NativeButton) event.getButton());
                container.addComponent(new TagsSearchComposite(null, MainLayout.this));

            }
        });
        askQuestion.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                container.removeAllComponents();
                setActive((NativeButton) event.getButton());
                container.addComponent(new AskQuestionComposite());

            }
        });

        logout_button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getSession().setAttribute("loggedin", null);
                UI.getCurrent().getNavigator().navigateTo(MyVaadinApp.LOGIN_VIEW);
            }
        });
    }

    private void setActive(NativeButton button){
        myQuestions.removeStyleName("active");
        topQuestions.removeStyleName("active");
        tags.removeStyleName("active");
        askQuestion.removeStyleName("active");
        button.addStyleName("active");
    }

    private void buildMainLayout(){
        mainLayout = new VerticalLayout();
        wrapper = new VerticalLayout();

        //menuContainer
        menuContainer = new HorizontalLayout();

        //myQuestions
        myQuestions = new NativeButton("My Questions");
        myQuestions.setStyleName(Reindeer.BUTTON_LINK);
        myQuestions.addStyleName("menu-button");
        menuContainer.addComponent(myQuestions);

        //topQuestions
        topQuestions = new NativeButton("Top Questions");
        topQuestions.setStyleName(Reindeer.BUTTON_LINK);
        topQuestions.addStyleName("menu-button");
        menuContainer.addComponent(topQuestions);

        //tags
        tags = new NativeButton("Tags");
        tags.setStyleName(Reindeer.BUTTON_LINK);
        tags.addStyleName("menu-button");
        menuContainer.addComponent(tags);

        //askQuestion
        askQuestion = new NativeButton("Ask Question");
        askQuestion.setStyleName(Reindeer.BUTTON_LINK);
        askQuestion.addStyleName("menu-button");
        menuContainer.addComponent(askQuestion);

        //container
        container = new VerticalLayout();

        //header
        header = new HorizontalLayout();
        header.setWidth("900px");
        header.setHeight("50px");

        //logo
        nil = new Label("Null");
        nil.setWidth("-1px");
        nil.setHeight("-1px");
        nil.setStyleName("null");
        header.addComponent(nil);

        //logo
        pointer = new Label("Pointer");
        pointer.setStyleName("pointer");
        pointer.setHeight("-1px");
        header.addComponent(pointer);
        header.setExpandRatio(pointer, 1.0f);



        //username
        username = new Label();
        username.setWidth("-1px");
        username.setHeight("-1px");
        username.setStyleName("header-username");
        header.addComponent(username);
        header.setComponentAlignment(username, Alignment.BOTTOM_RIGHT);

        //logout
        logout_button = new NativeButton("logout");
        logout_button.setStyleName(Reindeer.BUTTON_LINK);
        logout_button.addStyleName("header-logout");
        header.addComponent(logout_button);
        header.setComponentAlignment(logout_button, Alignment.BOTTOM_RIGHT);


        mainLayout.addComponent(header);
        mainLayout.addComponent(menuContainer);
        mainLayout.addComponent(container);
        mainLayout.setWidth("900px");
        mainLayout.setStyleName("main-container");
        wrapper.addComponent(mainLayout);
        wrapper.setComponentAlignment(mainLayout, Alignment.TOP_CENTER);
    }

    public VerticalLayout getContainer(){
        return container;
    }



}
