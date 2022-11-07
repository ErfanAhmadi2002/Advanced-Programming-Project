package apps.controller.setting;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.LastSeenEvent;
import shared.responses.settingResponses.LastSeenResponse;

public class LastSeenController extends Controller {

    public LastSeenController() {
    }

    public void makeLastSeenEveryBody() {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            LastSeenEvent lastSeenEvent = new LastSeenEvent(LogicalAgent.authToken, 1);
            LastSeenResponse lastSeenResponse = (LastSeenResponse) socketEventSender.request(lastSeenEvent);
        }else {
            LastSeenEvent lastSeenEvent = new LastSeenEvent(0, 1);
            LogicalAgent.offlineController.saveEvent(lastSeenEvent , 5);
        }
    }

    public void makeLastSeenNoBody() {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            LastSeenEvent lastSeenEvent = new LastSeenEvent(LogicalAgent.authToken, 3);
            LastSeenResponse lastSeenResponse = (LastSeenResponse) socketEventSender.request(lastSeenEvent);
        }else {
            LastSeenEvent lastSeenEvent = new LastSeenEvent(0, 3);
            LogicalAgent.offlineController.saveEvent(lastSeenEvent , 5);
        }
    }

    public void makeLastSeenOnlyFollowers() {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            LastSeenEvent lastSeenEvent = new LastSeenEvent(LogicalAgent.authToken, 2);
            LastSeenResponse lastSeenResponse = (LastSeenResponse) socketEventSender.request(lastSeenEvent);
        }else {
            LastSeenEvent lastSeenEvent = new LastSeenEvent(0, 2);
            LogicalAgent.offlineController.saveEvent(lastSeenEvent , 5);
        }
    }
}
