package apps.view.pages.timeline;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.timelineListeners.commentListeners.ForwardMessageListener;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.Tweet;
import shared.models.TweetCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForwardMessage extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener submitButtonListener;
    private TweetCopy currentTweet;

    @FXML
    private TextField username;

    @FXML
    private Text resultText;

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
        submitButtonListener.eventOccurred(this);
    }

    public TweetCopy getCurrentTweet() {
        return currentTweet;
    }

    public TextField getUsername() {
        return username;
    }

    public void setCurrentTweet(TweetCopy currentTweet) {
        this.currentTweet = currentTweet;
    }

    public void showResult (boolean sentSuccessfully){
        Config config = Config.getConfig("general");
        if (sentSuccessfully){
            String text = config.getProperty(String.class , "sent");
            resultText.setText(text);
        }else {
            String text = config.getProperty(String.class , "fail");
            resultText.setText(text);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.submitButtonListener = new ForwardMessageListener();
    }
}
