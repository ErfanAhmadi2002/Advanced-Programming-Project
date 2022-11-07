package apps.listeners.messagingListeners.groupMessaging;

import apps.controller.messaging.GroupMessagingController;
import apps.view.pages.messaging.groupMessaging.CreateNewGroupMessagePage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class WriteNewMessageInGroupListener implements PageListener {
    private GroupMessagingController groupMessagingController;
    @Override
    public void eventOccurred(Page source) {
        groupMessagingController = new GroupMessagingController();
        groupMessagingController.writeNewMessageInGroup((CreateNewGroupMessagePage) source);
    }
}
