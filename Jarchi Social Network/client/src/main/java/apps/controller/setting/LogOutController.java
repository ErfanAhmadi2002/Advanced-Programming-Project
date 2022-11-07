package apps.controller.setting;

import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.settingEvents.LogOutEvent;
import shared.responses.settingResponses.LogOutResponse;

public class LogOutController extends Controller {

    public LogOutController() {
    }

    public void logOut (){
        if (LogicalAgent.socketEventSender != null) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            LogOutEvent logOutEvent = new LogOutEvent(LogicalAgent.authToken);
            LogOutResponse logOutResponse = (LogOutResponse) socketEventSender.request(logOutEvent);
            LogicalAgent.viewManager.getScenes().removeAllElements();
            LogicalAgent.viewManager.LoadScene("welcome");
        }
    }
}
