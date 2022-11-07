package managers;

import config.Config;
import database.Context;
import network.SocketResponseSender;
import shared.models.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.HashMap;

public class SocketManager extends Thread{

    public void run() {
        try {
            Config config = Config.getConfig("network");
            ServerSocket serverSocket = new ServerSocket(config.getProperty(Integer.class , "port"));
            SecureRandom secureRandom = new SecureRandom();
            HashMap<Integer , User> onlineUsers = new HashMap<>();
            Context context = new Context();
            BotManager botManager = new BotManager(context , onlineUsers , secureRandom);
            CliManager cliManager = new CliManager(botManager);
            cliManager.start();
            while (true) {
                Socket socket = serverSocket.accept();
                SocketResponseSender socketResponseSender = new SocketResponseSender(socket);
                ClientManager clientManager = new ClientManager(socketResponseSender,secureRandom , context , onlineUsers , botManager);
                clientManager.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}