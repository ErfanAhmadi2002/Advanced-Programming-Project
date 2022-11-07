package apps.controller.setting;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.GeneralPrivacyEvent;
import shared.responses.settingResponses.GeneralPrivacyResponse;

public class GeneralPrivacyController extends Controller {

    public GeneralPrivacyController() {
    }

    public void makeAccountPublic() {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            GeneralPrivacyEvent generalPrivacyEvent = new GeneralPrivacyEvent(LogicalAgent.authToken, true);
            GeneralPrivacyResponse generalPrivacyResponse = (GeneralPrivacyResponse) socketEventSender.request(generalPrivacyEvent);
        }else {
            GeneralPrivacyEvent generalPrivacyEvent = new GeneralPrivacyEvent(0, true);
            LogicalAgent.offlineController.saveEvent(generalPrivacyEvent , 3);
        }
    }

    public void makeAccountPrivate() {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            GeneralPrivacyEvent generalPrivacyEvent = new GeneralPrivacyEvent(LogicalAgent.authToken, false);
            GeneralPrivacyResponse generalPrivacyResponse = (GeneralPrivacyResponse) socketEventSender.request(generalPrivacyEvent);
        }else {
            GeneralPrivacyEvent generalPrivacyEvent = new GeneralPrivacyEvent(0, false);
            LogicalAgent.offlineController.saveEvent(generalPrivacyEvent , 3);
        }
    }


}
