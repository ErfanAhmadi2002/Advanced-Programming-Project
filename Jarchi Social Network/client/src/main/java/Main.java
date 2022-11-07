import config.Config;
import controller.LogicalAgent;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Config config = Config.getConfig("network");
            Socket socket = new Socket(config.getProperty(String.class, "ip"), config.getProperty(Integer.class, "port"));
            LogicalAgent logicalAgent = new LogicalAgent(primaryStage , socket);
            logicalAgent.start();
        }catch (Exception exception){
            LogicalAgent logicalAgent = new LogicalAgent(primaryStage , null);
            logicalAgent.start();
        }
    }
}
