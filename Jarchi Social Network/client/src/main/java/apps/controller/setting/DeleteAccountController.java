package apps.controller.setting;


import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.DeleteAccountEvent;
import shared.responses.settingResponses.DeleteAccountResponse;

public class DeleteAccountController extends Controller {

    public DeleteAccountController() {
    }

    public void deleteAccount() {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            DeleteAccountEvent deleteAccountEvent = new DeleteAccountEvent(LogicalAgent.authToken);
            DeleteAccountResponse deleteAccountResponse = (DeleteAccountResponse) socketEventSender.request(deleteAccountEvent);
            if (deleteAccountResponse.isDeleteAccountResult()) {
                System.exit(0);
            }
        }
    }
}
