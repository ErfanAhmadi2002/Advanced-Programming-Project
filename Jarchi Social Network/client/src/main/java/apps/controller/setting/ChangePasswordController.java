package apps.controller.setting;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.ChangePasswordEvent;
import shared.formEvents.NewPasswordFormEvent;
import shared.responses.settingResponses.ChangePasswordResponse;


public class ChangePasswordController extends Controller {

    public ChangePasswordController() {
    }

    public void changePassword (NewPasswordFormEvent formEvent) {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ChangePasswordEvent changePasswordEvent = new ChangePasswordEvent(LogicalAgent.authToken, formEvent);
            ChangePasswordResponse changePasswordResponse = (ChangePasswordResponse) socketEventSender.request(changePasswordEvent);
        }else {
            ChangePasswordEvent changePasswordEvent = new ChangePasswordEvent(0, formEvent);
            LogicalAgent.offlineController.saveEvent(changePasswordEvent , 2);
        }
    }
}
