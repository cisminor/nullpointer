package composite;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import domain.Tag;

public class TagComposite extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private NativeButton tag_button;
    private Tag tag;
    private MainLayout mainComposite;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public TagComposite(Tag tag, MainLayout mainComposite) {

		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
        this.tag = tag;
        this.mainComposite = mainComposite;
        setData();
        addButtonListeners();

	}

    private void setData(){
        tag_button.setCaption(tag.getTagName());
    }

    private void addButtonListeners(){
        tag_button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                mainComposite.getContainer().removeAllComponents();
                mainComposite.getContainer().addComponent(new TagsSearchComposite(tag, mainComposite));
            }
        });
    }

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// tag_button
		tag_button = new NativeButton();
		tag_button.setCaption("Tag Name");
		tag_button.setImmediate(false);
		tag_button.setWidth("-1px");
		tag_button.setHeight("-1px");
        tag_button.setStyleName("tag");
		mainLayout.addComponent(tag_button);
		
		return mainLayout;
	}

}
