package apps.view.pages.setting;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.settingListeners.itemPrivacy.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemPrivacy extends Page implements Initializable {
    private ButtonListener backButton;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ButtonListener publicBirthDayListener;
    private ButtonListener publicEmailListener;
    private ButtonListener publicPhoneNumberListener;
    private ButtonListener privateBirthDayListener;
    private ButtonListener privateEmailListener;
    private ButtonListener privatePhoneNumberListener;

    @FXML
    private Button back;

    @FXML
    private Button publicBirthday;

    @FXML
    private Button privateBirthday;

    @FXML
    private Button publicEmail;

    @FXML
    private Button publicPhoneNumber;

    @FXML
    private Button privateEmail;

    @FXML
    private Button privatePhoneNumber;

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
    void privateBirthday(ActionEvent event) throws IOException {
        privateBirthDayListener.buttonPressed();
    }

    @FXML
    void privateEmail(ActionEvent event) throws IOException {
        privateEmailListener.buttonPressed();
    }

    @FXML
    void privatePhoneNumber(ActionEvent event) throws IOException {
        privatePhoneNumberListener.buttonPressed();
    }

    @FXML
    void publicBirthday(ActionEvent event) throws IOException {
        publicBirthDayListener.buttonPressed();
    }

    @FXML
    void publicEmail(ActionEvent event) throws IOException {
        publicEmailListener.buttonPressed();
    }

    @FXML
    void publicPhoneNumber(ActionEvent event) throws IOException {
        publicPhoneNumberListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton = new GoBackListener();
        mainMenuListener = new GoToMainMenuListener();
        exitProgramListener = new ExitProgramListener();
        publicBirthDayListener = new MakeBirthdayPublicListener();
        privateBirthDayListener = new MakeBirthdayPrivateListener();
        publicEmailListener = new MakeEmailPublicListener();
        privateEmailListener = new MakeEmailPrivateListener();
        publicPhoneNumberListener = new MakePhoneNumberPublicListener();
        privatePhoneNumberListener = new MakePhoneNumberPrivateListener();
    }
}
