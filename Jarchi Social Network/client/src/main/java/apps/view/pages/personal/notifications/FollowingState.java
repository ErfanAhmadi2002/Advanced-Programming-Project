package apps.view.pages.personal.notifications;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.personalListeners.notifications.DeleteSeenNotificationFollowingStateListener;
import apps.listeners.personalListeners.notifications.StartFollowingStatePage;
import controller.LogicalAgent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.UserCopy;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FollowingState extends Page implements Initializable {
    private VBox vBox;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ButtonListener deleteSeenNotificationFollowingState;
    private PageListener startFollowingState;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitProgramListener.buttonPressed();
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        mainMenuListener.buttonPressed();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        LogicalAgent.finishFirstThread();
        deleteSeenNotificationFollowingState.buttonPressed();
        backButtonListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.startFollowingState = new StartFollowingStatePage();
        this.deleteSeenNotificationFollowingState = new DeleteSeenNotificationFollowingStateListener();
        startFollowingState.eventOccurred(this);
    }

    public void showFollowers (HashMap<UserCopy, Boolean> followOrUnfollow) {
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (UserCopy user:followOrUnfollow.keySet()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "followingNotificationPane")));
                AnchorPane pane = null;
                try {
                    pane = fxmlLoader.load();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                FollowingNotificationPane controller = fxmlLoader.getController();
                controller.startPage(user , followOrUnfollow.get(user));
                vBox.getChildren().add(pane);
            }
        });
    }
}
