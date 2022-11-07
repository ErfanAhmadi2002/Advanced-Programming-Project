package apps.view.pages.setting;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.settingListeners.lastSeen.MakeLastSeenEverybodyListener;
import apps.listeners.settingListeners.lastSeen.MakeLastSeenNobodyListener;
import apps.listeners.settingListeners.lastSeen.MakeLastSeenOnlyFollowersListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LastSeen extends Page implements Initializable {
    private ButtonListener backButton;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ButtonListener everybodyListener;
    private ButtonListener nobodyListener;
    private ButtonListener onlyFollowersListener;

    @FXML
    private Button back;

    @FXML
    private Button everybody;

    @FXML
    private Button nobody;

    @FXML
    private Button onlyFollowers;

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
        backButton.buttonPressed();
    }

    @FXML
    void onlyFollowers(ActionEvent event) throws IOException {
        onlyFollowersListener.buttonPressed();
    }

    @FXML
    void nobody(ActionEvent event) throws IOException {
        nobodyListener.buttonPressed();
    }

    @FXML
    void everybody(ActionEvent event) throws IOException {
        everybodyListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButton = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.everybodyListener = new MakeLastSeenEverybodyListener();
        this.nobodyListener = new MakeLastSeenNobodyListener();
        this.onlyFollowersListener = new MakeLastSeenOnlyFollowersListener();
    }
}
