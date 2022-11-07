package apps.controller.general;

import config.Config;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;

import java.net.Socket;

public class ReconnectController extends Controller {

    public void reconnect (){
        try {
            if (LogicalAgent.socketEventSender == null) {
                Config config = Config.getConfig("network");
                Socket socket = new Socket(config.getProperty(String.class, "ip"), config.getProperty(Integer.class, "port"));
                SocketEventSender socketEventSender = new SocketEventSender(socket);
                LogicalAgent.setSocketEventSender(socketEventSender);
                LogicalAgent.viewManager.LoadScene("welcome");
            }
        }catch (Throwable ignored){}
    }
}
