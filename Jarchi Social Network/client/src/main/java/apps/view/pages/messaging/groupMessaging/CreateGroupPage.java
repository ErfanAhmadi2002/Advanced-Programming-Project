package apps.view.pages.messaging.groupMessaging;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.groupMessaging.AddGroupMemberListener;
import apps.listeners.messagingListeners.groupMessaging.CreateNewGroupListener;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateGroupPage extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ArrayList<Group> allGroups;
    private PageListener addButtonListener;
    private PageListener submitButtonListener;

    @FXML
    private TextField username;

    @FXML
    private Button back;

    @FXML
    private Button submit;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Label userNameLabel;

    @FXML
    private TextField groupName;

    @FXML
    private Label groupNameLabel;

    @FXML
    private Button add;

    public TextField getUsername() {
        return username;
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }

    public Label getGroupNameLabel() {
        return groupNameLabel;
    }

    public TextField getGroupName() {
        return groupName;
    }

    public ArrayList<Group> getAllGroups() {
        return allGroups;
    }

    public void setAllGroups(ArrayList<Group> allGroups) {
        this.allGroups = allGroups;
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        addButtonListener.eventOccurred(this);
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

    @FXML
    void submit(ActionEvent event) throws IOException {
        submitButtonListener.eventOccurred(this);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.addButtonListener = new AddGroupMemberListener();
        this.submitButtonListener = new CreateNewGroupListener();
    }
}
