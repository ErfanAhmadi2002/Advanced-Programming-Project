package apps.listeners.messagingListeners.groupMessaging;

import apps.controller.messaging.GroupMessagingController;
import apps.view.pages.messaging.groupMessaging.AddGroupMemberPage;
import apps.view.pages.messaging.groupMessaging.CreateGroupPage;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class AddGroupMemberListener implements PageListener {
    private GroupMessagingController groupMessagingController;

    @Override
    public void eventOccurred(Page source) {
        groupMessagingController = new GroupMessagingController();
        if (source.getClass().equals(AddGroupMemberPage.class)) {
            groupMessagingController.addMemberToGroup((AddGroupMemberPage) source);
        }else {
            groupMessagingController.addMemberToNewGroup((CreateGroupPage) source);
        }
    }
}
