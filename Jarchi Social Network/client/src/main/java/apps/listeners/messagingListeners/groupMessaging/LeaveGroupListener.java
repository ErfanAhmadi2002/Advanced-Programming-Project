package apps.listeners.messagingListeners.groupMessaging;

import apps.controller.messaging.GroupMessagingController;
import apps.view.pages.messaging.groupMessaging.GroupPane;
import listeners.PageListener;
import view.Page;

public class LeaveGroupListener implements PageListener {
    private GroupMessagingController groupMessagingController;

    @Override
    public void eventOccurred(Page source) {
        groupMessagingController = new GroupMessagingController();
        groupMessagingController.leaveGroup((GroupPane) source);
    }
}
