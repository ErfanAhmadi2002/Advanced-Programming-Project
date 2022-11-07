package apps.listeners.messagingListeners.startPages;

import apps.controller.messaging.SavedMessagesController;
import apps.view.pages.messaging.savedMessages.SavedMessagesPane;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class StartSavedMessagesPaneListener implements PageListener {
    private SavedMessagesController savedMessagesController;
    @Override
    public void eventOccurred(Page source) {
        savedMessagesController = new SavedMessagesController();
        savedMessagesController.startSavedMessagesPane((SavedMessagesPane) source);
    }
}
