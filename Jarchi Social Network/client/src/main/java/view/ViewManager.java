package view;

import config.Config;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.models.User;
import shared.models.UserCopy;

import java.io.IOException;
import java.util.Stack;

public class ViewManager {
    private UserCopy user;
    private Stage mainStage;
    private Config fxmlConfig;
    private Stack<Scene> scenes;

    public ViewManager(Stage stage){
        mainStage = stage;
        scenes = new Stack<>();
        this.fxmlConfig = Config.getConfig("fxmlAddress");
    }

    public void start(boolean isOnline) {
        if (isOnline) {
            LoadScene("welcome");
        }else {
            LoadScene("mainMenu");
        }
        mainStage.show();
    }

    public UserCopy getUser() {
        return user;
    }

    public void setUser(UserCopy user) {
        this.user = user;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Config getFxmlConfig() {
        return fxmlConfig;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Stack<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(Stack<Scene> scenes) {
        this.scenes = scenes;
    }

    public void back(){
        scenes.pop();
        mainStage.setScene(scenes.peek());
    }

    public void LoadScene(String name) {
        Platform.runLater(() -> {
            String url = fxmlConfig.getProperty(String.class , name);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(url));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            assert root != null;
            Scene scene = new Scene(root);
            scenes.push(scene);
            mainStage.setScene(scene);
        });
    }

}
