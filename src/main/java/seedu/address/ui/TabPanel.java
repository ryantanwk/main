package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;

/**
 * TODO: add BrowserPanel into the first tab during instantiation
 */
public class TabPanel extends  UiPart<Region> {

    private static final String FXML = "TabPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private BrowserPanel browserPanel;

    private EventListPanel eventListPanel;

    @FXML
    private TabPane tabPane;

    private SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

    @FXML
    private Tab webpageTab;

    @FXML
    private Tab eventsTab;

    public TabPanel() {
        super(FXML);

        browserPanel = new BrowserPanel();
        webpageTab.setContent(browserPanel.getRoot());

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        registerAsAnEventHandler(this);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public Tab getWebpageTab() {
        return webpageTab;
    }

    public Tab getEventsTab() {
        return eventsTab;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        // switch active tab
        selectionModel.select(webpageTab);
    }
}
