package apps.view.pages.mainEntrancePages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.settingListeners.pageChanger.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Setting extends Page implements Initializable {
    private ButtonListener itemPrivacyButton;
    private ButtonListener lastSeenButton;
    private ButtonListener accountActivationButton;
    private ButtonListener changePasswordButton;
    private ButtonListener deleteAccountButton;
    private ButtonListener logOutButton;
    private ButtonListener generalPrivacyButton;
    private ButtonListener backButton;
    private ButtonListener exitButton;

    @FXML
    private Button itemPrivacy;

    @FXML
    private Button lastSeen;

    @FXML
    private Button accountActivation;

    @FXML
    private Button changePassword;

    @FXML
    private Button deleteAccount;

    @FXML
    private Button logOut;

    @FXML
    private Button generalPrivacy;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitButton.buttonPressed();
    }

    @FXML
    void accountActivation(ActionEvent event) throws IOException {
        accountActivationButton.buttonPressed();
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
        changePasswordButton.buttonPressed();
    }

    @FXML
    void deleteAccount(ActionEvent event) throws IOException {
        deleteAccountButton.buttonPressed();
    }

    @FXML
    void generalPrivacy(ActionEvent event) throws IOException{
        generalPrivacyButton.buttonPressed();
    }

    @FXML
    void itemPrivacy(ActionEvent event) throws IOException{
        itemPrivacyButton.buttonPressed();
    }

    @FXML
    void lastSeen(ActionEvent event) throws IOException{
        lastSeenButton.buttonPressed();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException{
        logOutButton.buttonPressed();
    }

    @FXML
    void back(ActionEvent event) throws IOException{
        backButton.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemPrivacyButton = new GoToItemPrivacyListener();
        lastSeenButton = new GoToLastSeenListener();
        logOutButton = new GoToLogOutListener();
        generalPrivacyButton = new GoToGeneralPrivacyListener();
        deleteAccountButton = new GoToDeleteAccountListener();
        changePasswordButton = new GoToChangePasswordListener();
        accountActivationButton = new GoToAccountActivationListener();
        backButton = new GoBackListener();
        exitButton = new ExitProgramListener();
    }
}
