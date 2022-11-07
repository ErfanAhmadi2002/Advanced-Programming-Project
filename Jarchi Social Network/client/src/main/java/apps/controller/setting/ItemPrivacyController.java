package apps.controller.setting;


import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.ItemPrivacyEvent;
import shared.responses.settingResponses.ItemPrivacyResponse;


public class ItemPrivacyController extends Controller {

    public ItemPrivacyController() {
    }

    public void makeBirthDayPublic () {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(LogicalAgent.authToken, true, 0);
            ItemPrivacyResponse itemPrivacyResponse = (ItemPrivacyResponse) socketEventSender.request(itemPrivacyEvent);
        }else {
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(0, true, 0);
            LogicalAgent.offlineController.saveEvent(itemPrivacyEvent , 4);
        }
    }

    public void makePhoneNumberPublic () {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(LogicalAgent.authToken , true , 2);
            ItemPrivacyResponse itemPrivacyResponse = (ItemPrivacyResponse) socketEventSender.request(itemPrivacyEvent);
        }else {
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(0, true, 2);
            LogicalAgent.offlineController.saveEvent(itemPrivacyEvent , 4);
        }
    }

    public void makeEmailPublic () {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(LogicalAgent.authToken, true, 1);
            ItemPrivacyResponse itemPrivacyResponse = (ItemPrivacyResponse) socketEventSender.request(itemPrivacyEvent);
        }else {
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(0, true, 1);
            LogicalAgent.offlineController.saveEvent(itemPrivacyEvent , 4);
        }
    }

    public void makeBirthDayPrivate () {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(LogicalAgent.authToken , false , 0);
            ItemPrivacyResponse itemPrivacyResponse = (ItemPrivacyResponse) socketEventSender.request(itemPrivacyEvent);
        }else {
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(0, false, 0);
            LogicalAgent.offlineController.saveEvent(itemPrivacyEvent , 4);
        }
    }

    public void makePhoneNumberPrivate () {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(LogicalAgent.authToken , false , 2);
            ItemPrivacyResponse itemPrivacyResponse = (ItemPrivacyResponse) socketEventSender.request(itemPrivacyEvent);
        }else {
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(0, false, 2);
            LogicalAgent.offlineController.saveEvent(itemPrivacyEvent , 4);
        }
    }

    public void makeEmailPrivate () {
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(LogicalAgent.authToken , false , 1);
            ItemPrivacyResponse itemPrivacyResponse = (ItemPrivacyResponse) socketEventSender.request(itemPrivacyEvent);
        }else {
            ItemPrivacyEvent itemPrivacyEvent = new ItemPrivacyEvent(0, false, 1);
            LogicalAgent.offlineController.saveEvent(itemPrivacyEvent , 4);
        }
    }

}
