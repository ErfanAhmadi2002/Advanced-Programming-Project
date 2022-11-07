package apps.controller.setting;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.AccountActivationEvent;
import shared.responses.settingResponses.AccountActivationResponse;


public class AccountActivationController extends Controller {

    public AccountActivationController() {
    }

    public void activateAccount () {
        if (LogicalAgent.socketEventSender != null){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            AccountActivationEvent accountActivationEvent = new AccountActivationEvent(LogicalAgent.authToken , true);
            AccountActivationResponse accountActivationResponse = (AccountActivationResponse) socketEventSender.request(accountActivationEvent);
        }else {
            AccountActivationEvent accountActivationEvent = new AccountActivationEvent(0 , true);
            LogicalAgent.offlineController.saveEvent(accountActivationEvent , 1);
        }

    }

    public void deactivateAccount (){
        if (LogicalAgent.socketEventSender != null){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            AccountActivationEvent accountActivationEvent = new AccountActivationEvent(LogicalAgent.authToken , false);
            AccountActivationResponse accountActivationResponse = (AccountActivationResponse) socketEventSender.request(accountActivationEvent);
        }else {
            AccountActivationEvent accountActivationEvent = new AccountActivationEvent(0 , false);
            LogicalAgent.offlineController.saveEvent(accountActivationEvent , 1);
        }
    }
}
