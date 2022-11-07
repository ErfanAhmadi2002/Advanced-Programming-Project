package apps.view.pages.messaging.groupMessaging;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.groupMessaging.AddGroupMemberListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.Group;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddGroupMemberPage extends Page implements Initializable {
    private Group group;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener addGroupMemberListener;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private TextField username;

    @FXML
    private Button add;

    @FXML
    private Label userNameLabel;

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public TextField getUsername() {
        return username;
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        addGroupMemberListener.eventOccurred(this);
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.addGroupMemberListener = new AddGroupMemberListener();
    }
}
