package apps.view.pages.mainEntrancePages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.messagingListeners.pageChanger.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Messaging extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener exitButtonListener;
    private ButtonListener savedMessagesButtonListener;
    private ButtonListener messageWithKnownButtonListener;
    private ButtonListener messageWithUnknownButtonListener;
    private ButtonListener groupMessagingButtonListener;
    private ButtonListener multipleMessagingButtonListener;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button savedMessages;

    @FXML
    private Button messageWithKnown;

    @FXML
    private Button messageWithUnknown;

    @FXML
    private Button groupMessaging;

    @FXML
    private Button multipleMessaging;

    @FXML
    void back(ActionEvent event) throws IOException {
        backButtonListener.buttonPressed();
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitButtonListener.buttonPressed();
    }

    @FXML
    void groupMessaging(ActionEvent event) throws IOException {
        groupMessagingButtonListener.buttonPressed();
    }

    @FXML
    void messageWithKnown(ActionEvent event) throws IOException {
        messageWithKnownButtonListener.buttonPressed();
    }

    @FXML
    void messageWithUnknown(ActionEvent event) throws IOException {
        messageWithUnknownButtonListener.buttonPressed();
    }

    @FXML
    void multipleMessaging(ActionEvent event) throws IOException {
        multipleMessagingButtonListener.buttonPressed();
    }

    @FXML
    void savedMessages(ActionEvent event) throws IOException {
        savedMessagesButtonListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButtonListener = new GoBackListener();
        exitButtonListener = new ExitProgramListener();
        savedMessagesButtonListener = new GoToSavedMessages();
        messageWithKnownButtonListener = new GoToMessageWithKnown();
        messageWithUnknownButtonListener = new GoToMessageWithUnknown();
        multipleMessagingButtonListener = new GoToMultipleMessaging();
        groupMessagingButtonListener = new GoToGroupMessaging();
    }
}
