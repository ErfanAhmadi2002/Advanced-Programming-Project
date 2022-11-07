package apps.view.pages.setting;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.settingListeners.deleteAccount.DeleteAccountListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteAccount extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ButtonListener deleteAccountListener;

    @FXML
    private Button back;

    @FXML
    private Button deleteAccount;

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
        backButtonListener.buttonPressed();
    }

    @FXML
    void deleteAccount(ActionEvent event) throws IOException {
        deleteAccountListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButtonListener = new GoBackListener();
        mainMenuListener = new GoToMainMenuListener();
        exitProgramListener = new ExitProgramListener();
        deleteAccountListener = new DeleteAccountListener();
    }
}
