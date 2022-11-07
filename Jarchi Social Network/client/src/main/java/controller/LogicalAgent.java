package controller;

import config.Config;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import network.ResponseVisitor;
import network.SocketEventSender;
import syncHandler.SyncHandler;
import view.ViewManager;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class LogicalAgent {
    public static ViewManager viewManager;
    public static SocketEventSender socketEventSender;
    public static ArrayList<ResponseVisitor> responseVisitors;
    public static OfflineController offlineController;
    public static SyncHandler syncHandler;
    public static int authToken;
    private Stage stage;

    public LogicalAgent(Stage stage, Socket socket) {
        syncHandler = new SyncHandler();
        responseVisitors = new ArrayList<>();
        offlineController = new OfflineController();
        authToken = -1;
        this.stage = stage;
        try {
            if (socket != null) {
                socketEventSender = new SocketEventSender(socket);
                viewManager = new ViewManager(stage);
            } else {
                socketEventSender = null;
                viewManager = new ViewManager(stage);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void setSocketEventSender(SocketEventSender socketEventSender) {
        LogicalAgent.socketEventSender = socketEventSender;
    }

    public void start() {
        Config config = Config.getConfig("dataBaseAddress");
        stage.setTitle("Jarchi");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.getIcons().add(new Image(config.getProperty(String.class, "mainIcon")));
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> System.exit(0));
        viewManager.start(socketEventSender != null);
    }

    public static void finishFirstThread() {
        responseVisitors.get(0).finishThread();
        responseVisitors.remove(0);
    }

    public static void pauseFirstThread() {
        responseVisitors.get(0).pauseThread();
    }

    public static void finishLastThread() {
        responseVisitors.get(responseVisitors.size() - 1).finishThread();
        responseVisitors.remove(responseVisitors.size() - 1);
    }

    public static void stopAllThreads() {
        for (ResponseVisitor responseVisitor : responseVisitors) {
            responseVisitor.finishThread();
        }
        responseVisitors.clear();
    }

    public static void pauseLastThread() {
        responseVisitors.get(responseVisitors.size() - 1).pauseThread();
    }

    public static void continueLastThread() {
        responseVisitors.get(responseVisitors.size() - 1).continueThread();
    }

}
