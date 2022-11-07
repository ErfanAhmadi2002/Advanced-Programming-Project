package apps.listeners.messagingListeners.pageChanger;

import apps.view.pages.messaging.groupMessaging.GroupPane;
import listeners.PageListener;
import shared.models.Group;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;

public class GoToAddGroupMemberPage implements PageListener {
    @Override
    public void eventOccurred(Page source) {
        Group group = ((GroupPane)source).getGroup();
        ExtraPageChangers.goToAddGroupMemberPage(group);
    }
}
