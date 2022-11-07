package apps.view.pages.timeline;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.timelineListeners.commentListeners.WriteNewCommentListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import listeners.ButtonListener;
import listeners.FormListener;
import shared.formEvents.NewCommentFormEvent;
import shared.models.Tweet;
import shared.models.TweetCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WriteComment extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private FormListener submitButtonListener;
    private TweetCopy currentTweet;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button submit;

    @FXML
    private TextField commentText;


    public TweetCopy getCurrentTweet() {
        return currentTweet;
    }
    public void setCurrentTweet(TweetCopy currentTweet) {
        this.currentTweet = currentTweet;
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
        NewCommentFormEvent formEvent = new NewCommentFormEvent(commentText.getText());
        submitButtonListener.formRequest(formEvent , this);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.submitButtonListener = new WriteNewCommentListener();
    }
}
