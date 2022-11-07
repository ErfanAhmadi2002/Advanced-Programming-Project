package apps.view.pages.setting;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.settingListeners.accountActivation.ActivateListener;
import apps.listeners.settingListeners.accountActivation.DeactivateListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountActivation extends Page implements Initializable {
    private ButtonListener backListener;
    private ButtonListener activateListener;
    private ButtonListener deactivateListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;

    @FXML
    private Button activate;

    @FXML
    private Button deactivate;

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
    void activate(ActionEvent event) throws IOException {
        activateListener.buttonPressed();
    }

    @FXML
    void deactivate(ActionEvent event) throws IOException {
        deactivateListener.buttonPressed();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        backListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backListener = new GoBackListener();
        this.activateListener = new ActivateListener();
        this.deactivateListener = new DeactivateListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
    }
}
