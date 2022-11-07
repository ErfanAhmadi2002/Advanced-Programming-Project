package apps.listeners.messagingListeners.multipleMessaging;

import apps.controller.messaging.MultipleMessagingController;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.ExtraPageChangers;
import view.Page;

import java.io.IOException;
import java.util.ArrayList;

public class SendMessageToAllListener implements PageListener {
    private MultipleMessagingController multipleMessagingController;
    @Override
    public void eventOccurred(Page source) {
        multipleMessagingController = new MultipleMessagingController();
        ArrayList<UserCopy> usersToBeSent = multipleMessagingController.getAllFollowers();
        ExtraPageChangers.goToCreateMultipleMessage(usersToBeSent);
    }
}
