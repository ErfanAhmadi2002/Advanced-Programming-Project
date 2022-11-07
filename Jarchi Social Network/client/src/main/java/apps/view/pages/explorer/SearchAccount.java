package apps.view.pages.explorer;

import apps.listeners.explorerListeners.SearchAccountListener;
import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import config.Config;
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

public class SearchAccount extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener searchButtonListener;


    @FXML
    private TextField username;

    @FXML
    private Button back;

    @FXML
    private Button search;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Label userNameLabel;

    public TextField getUsername() {
        return username;
    }
    public Label getUserNameLabel() {
        return userNameLabel;
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
    void search(ActionEvent event) throws IOException {
        searchButtonListener.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.searchButtonListener = new SearchAccountListener();
    }

    public void errorOccurred (){
        Config config = Config.getConfig("errorMessages");
        this.getUserNameLabel().setText(config.getProperty(String.class , "accountNotExist"));
    }
}
