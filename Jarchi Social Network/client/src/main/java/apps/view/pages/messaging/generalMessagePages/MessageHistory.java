package apps.view.pages.messaging.generalMessagePages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.pageChanger.GoToCreateNewMessagePage;
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
import shared.models.MessageCopy;
import shared.models.UserCopy;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessageHistory extends Page implements Initializable {
    private VBox vBox;
    private ArrayList<UserCopy> usersInChat;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener createNewMessageListener;

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
        if (LogicalAgent.responseVisitors.size() == 2){
            LogicalAgent.finishLastThread();
            LogicalAgent.responseVisitors.get(0).continueThread();
        }else {LogicalAgent.finishFirstThread();}
        backButtonListener.buttonPressed();
    }

    @FXML
    void createNewMessage(ActionEvent event) {
        LogicalAgent.pauseLastThread();
        createNewMessageListener.eventOccurred(this);
    }


    public void setUsersInChat(ArrayList<UserCopy> usersInChat) {
        this.usersInChat = usersInChat;
    }

    public ArrayList<UserCopy> getUsersInChat() {
        return usersInChat;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.createNewMessageListener = new GoToCreateNewMessagePage();
    }

    public void showMessages (ArrayList<MessageCopy> messages) {
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (MessageCopy message:messages){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty("messagePane")));
                    AnchorPane pane = fxmlLoader.load();
                    MessagePane controller = fxmlLoader.getController();
                    controller.startPage(message, usersInChat , false);
                    vBox.getChildren().add(pane);
                }catch (Throwable throwable){
                    throwable.printStackTrace();
                }
            }
        });

    }
}
