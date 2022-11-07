package apps.view.pages.messaging.messageWithKnown;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.startPages.StartUserNamesAndUnreadMessagesPageListener;
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
import shared.models.Message;
import shared.models.UserCopy;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UserNamesAndUnreadMessages extends Page implements Initializable {
    private VBox vBox;
    private HashMap<UserCopy, ArrayList<Message>> allMessages;
    private PageListener startUserNamesAndUnreadMessagesPageListener;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;

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
        backButtonListener.buttonPressed();
    }

    public void setAllMessages(HashMap<UserCopy, ArrayList<Message>> allMessages) {
        this.allMessages = allMessages;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.startUserNamesAndUnreadMessagesPageListener = new StartUserNamesAndUnreadMessagesPageListener();
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        startUserNamesAndUnreadMessagesPageListener.eventOccurred(this);
    }

    public void showTheList() {
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (UserCopy user : allMessages.keySet()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class, "userNamesAndUnreadMessagesPane")));
                try {
                    AnchorPane pane = fxmlLoader.load();
                    UserNamesAndUnreadMessagesPane controller = fxmlLoader.getController();
                    controller.startPage(user, allMessages.get(user));
                    vBox.getChildren().add(pane);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
