package apps.view.pages.messaging.groupMessaging;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.pageChanger.GoToCreateNewGroupPage;
import apps.listeners.messagingListeners.startPages.StartGroupMessagingPageListener;
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
import shared.models.Group;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupMessaging extends Page implements Initializable {
    private VBox vBox;
    private ArrayList<Group> allGroups;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener startGroupMessagingListener;
    private PageListener createNewGroupListener;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button createNewGroup;

    public void setAllGroups(ArrayList<Group> allGroups) {
        this.allGroups = allGroups;
    }

    public ArrayList<Group> getAllGroups() {
        return allGroups;
    }

    @FXML
    void createNewGroup(ActionEvent event) {
        LogicalAgent.pauseLastThread();
        createNewGroupListener.eventOccurred(this);
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.createNewGroupListener = new GoToCreateNewGroupPage();
        startGroupMessagingListener = new StartGroupMessagingPageListener();
        startGroupMessagingListener.eventOccurred(this);
    }

    public void showGroups(ArrayList<Group> allGroups) {
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (Group group : allGroups) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class, "groupPane")));
                    AnchorPane pane = fxmlLoader.load();
                    GroupPane controller = fxmlLoader.getController();
                    controller.startPage(group);
                    vBox.getChildren().add(pane);
                } catch (Throwable ignored) {
                }
            }
        });
    }
}
