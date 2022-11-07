package apps.view.pages.messaging.groupMessaging;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.pageChanger.GoToCreateNewGroupMessagePage;
import apps.view.pages.messaging.generalMessagePages.MessagePane;
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
import shared.models.*;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupHistory extends Page implements Initializable {
    private VBox vBox;
    private Group group;
    private ArrayList<MessageCopy> messages;
    private ArrayList<UserCopy> usersInChat;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener createNewGroupMessageListener;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button createNewMessage;

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
        LogicalAgent.finishLastThread();
        LogicalAgent.continueLastThread();
        backButtonListener.buttonPressed();
    }

    @FXML
    void createNewMessage(ActionEvent event) {
        LogicalAgent.pauseLastThread();
        createNewGroupMessageListener.eventOccurred(this);
    }

    public void setMessages(ArrayList<MessageCopy> messages) {
        this.messages = messages;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public ArrayList<UserCopy> getUsersInChat() {
        return usersInChat;
    }

    public void setUsersInChat(ArrayList<UserCopy> usersInChat) {
        this.usersInChat = usersInChat;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.createNewGroupMessageListener = new GoToCreateNewGroupMessagePage();
    }

    public void showMessages () {
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (MessageCopy message:messages){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "messagePane")));
                    AnchorPane pane = fxmlLoader.load();
                    MessagePane controller = fxmlLoader.getController();
                    controller.startPage(message, usersInChat , true);
                    vBox.getChildren().add(pane);
                }catch (Throwable ignored){}
            }
        });
    }
}
