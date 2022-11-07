package apps.view.pages.setting;
import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.settingListeners.changePassword.ChangePasswordFormListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import listeners.ButtonListener;
import listeners.FormListener;
import shared.formEvents.NewPasswordFormEvent;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassword extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private FormListener changePasswordListener;

    @FXML
    private TextField newPassword;

    @FXML
    private Button back;

    @FXML
    private Button submit;

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
    void submit(ActionEvent event) throws IOException {
        NewPasswordFormEvent formEvent = new NewPasswordFormEvent(newPassword.getText());
        changePasswordListener.formRequest(formEvent , this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.changePasswordListener = new ChangePasswordFormListener();
    }
}
