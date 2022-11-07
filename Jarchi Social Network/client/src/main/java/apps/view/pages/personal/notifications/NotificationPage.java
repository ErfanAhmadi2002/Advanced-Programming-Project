package apps.view.pages.personal.notifications;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.personalListeners.notifications.GoToFollowingStatePage;
import apps.listeners.personalListeners.notifications.GoToMyRequestsPage;
import apps.listeners.personalListeners.notifications.GoToNewRequestsPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationPage extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ButtonListener goToNewRequestsPage;
    private ButtonListener goToMyRequestsPage;
    private ButtonListener goToFollowingStatePage;

    @FXML
    private Button newRequests;

    @FXML
    private Button myRequests;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button followingState;

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
    void followingState(ActionEvent event) throws IOException {
        goToFollowingStatePage.buttonPressed();
    }

    @FXML
    void myRequests(ActionEvent event) throws IOException {
        goToMyRequestsPage.buttonPressed();
    }

    @FXML
    void newRequests(ActionEvent event) throws IOException {
        goToNewRequestsPage.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.goToFollowingStatePage = new GoToFollowingStatePage();
        this.goToMyRequestsPage = new GoToMyRequestsPage();
        this.goToNewRequestsPage = new GoToNewRequestsPage();
    }
}
