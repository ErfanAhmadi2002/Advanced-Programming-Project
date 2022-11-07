package apps.view.pages.messaging.multipleMessaging;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.multipleMessaging.AddMemberToNewCategoryListener;
import apps.listeners.messagingListeners.multipleMessaging.CreateNewCategoryNameListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import listeners.ButtonListener;
import listeners.PageListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCategoryPage extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
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
    private TextField categoryName;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Button add;

    public TextField getUsername() {
        return username;
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }

    public TextField getCategoryName() {
        return categoryName;
    }

    public Label getCategoryNameLabel() {
        return categoryNameLabel;
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
        addButtonListener = new AddMemberToNewCategoryListener();
        submitButtonListener = new CreateNewCategoryNameListener();
    }
}
