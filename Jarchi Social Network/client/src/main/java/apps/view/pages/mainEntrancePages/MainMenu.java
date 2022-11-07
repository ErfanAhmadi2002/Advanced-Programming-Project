package apps.view.pages.mainEntrancePages;

import apps.listeners.mainMenuListeners.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu extends Page implements Initializable {
    private ButtonListener settingButtonListener;
    private ButtonListener personalButtonListener;
    private ButtonListener timeLineButtonListener;
    private ButtonListener explorerButtonListener;
    private ButtonListener messagingButtonListener;
    private ButtonListener reconnectButtonListener;

    @FXML
    private Button explorer;

    @FXML
    private Button timeLine;

    @FXML
    private Button personal;

    @FXML
    private Button reconnect;

    @FXML
    private Button messaging;

    @FXML
    private Button setting;

    @FXML
    void explorer(ActionEvent event) throws IOException {
        explorerButtonListener.buttonPressed();
    }

    @FXML
    void messaging(ActionEvent event) throws IOException {
        messagingButtonListener.buttonPressed();
    }

    @FXML
    void personal(ActionEvent event) throws IOException {
        personalButtonListener.buttonPressed();
    }

    @FXML
    void setting(ActionEvent event) throws IOException {
        settingButtonListener.buttonPressed();
    }

    @FXML
    void timeLine(ActionEvent event) throws IOException {
        timeLineButtonListener.buttonPressed();
    }

    @FXML
    void reconnect(ActionEvent event) {
        try {
            reconnectButtonListener.buttonPressed();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingButtonListener = new GoToSettingPageListener();
        personalButtonListener = new GoToPersonalPageListener();
        timeLineButtonListener = new GoToTimeLinePageListener();
        explorerButtonListener = new GoToExplorerPageListener();
        messagingButtonListener = new GoToMessagingPageListener();
        reconnectButtonListener = new ReconnectListener();
    }
}
