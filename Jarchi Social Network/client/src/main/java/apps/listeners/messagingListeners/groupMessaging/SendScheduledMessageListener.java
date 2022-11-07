package apps.listeners.messagingListeners.groupMessaging;

import apps.controller.messaging.GroupMessagingController;
import listeners.FormListener;
import shared.formEvents.Form;
import shared.formEvents.ScheduledMessageFormEvent;
import view.Page;

public class SendScheduledMessageListener implements FormListener {
    private GroupMessagingController groupMessagingController;

    @Override
    public void formRequest(Form form, Page source) {
        groupMessagingController = new GroupMessagingController();
        groupMessagingController.writeScheduledMessageInGroup((ScheduledMessageFormEvent) form);
    }
}
