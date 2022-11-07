package apps.view.pages.mainEntrancePages;

import apps.listeners.explorerListeners.GoToRandomTweetsPage;
import apps.listeners.explorerListeners.GoToSearchAccountPage;
import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Explorer extends Page implements Initializable {
    private ButtonListener randomTweetsButtonListener;
    private ButtonListener backButtonListener;
    private ButtonListener exitButtonListener;
    private ButtonListener goToSearchAccountListener;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button searchAccount;

    @FXML
    private Button randomTweets;

    @FXML
    void back(ActionEvent event) throws IOException {
        backButtonListener.buttonPressed();
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitButtonListener.buttonPressed();
    }

    @FXML
    void randomTweets(ActionEvent event) throws IOException {
        randomTweetsButtonListener.buttonPressed();
    }

    @FXML
    void searchAccount(ActionEvent event) throws IOException {
        goToSearchAccountListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomTweetsButtonListener = new GoToRandomTweetsPage();
        backButtonListener = new GoBackListener();
        exitButtonListener = new ExitProgramListener();
        goToSearchAccountListener = new GoToSearchAccountPage();
    }
}
