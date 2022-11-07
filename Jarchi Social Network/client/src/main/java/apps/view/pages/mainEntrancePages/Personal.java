package apps.view.pages.mainEntrancePages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.personalListeners.pageChanger.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Personal extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener exitButtonListener;
    private ButtonListener createNewTweetButtonListener;
    private ButtonListener myTweetsButtonListener;
    private ButtonListener editInfoButtonListener;
    private ButtonListener followersButtonListener;
    private ButtonListener followingsButtonListener;
    private ButtonListener blackListButtonListener;
    private ButtonListener myInfoButtonListener;
    private ButtonListener notificationsButtonListener;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button createNewTweet;

    @FXML
    private Button myTweets;

    @FXML
    private Button editInfo;

    @FXML
    private Button followers;

    @FXML
    private Button followings;

    @FXML
    private Button blacklist;

    @FXML
    private Button myInfo;

    @FXML
    private Button notifications;

    @FXML
    void back(ActionEvent event) throws IOException {
        backButtonListener.buttonPressed();
    }

    @FXML
    void blacklist(ActionEvent event) throws IOException{
        blackListButtonListener.buttonPressed();
    }

    @FXML
    void createNewTweet(ActionEvent event) throws IOException{
        createNewTweetButtonListener.buttonPressed();
    }

    @FXML
    void editInfo(ActionEvent event) throws IOException{
        editInfoButtonListener.buttonPressed();
    }

    @FXML
    void exit(ActionEvent event) throws IOException{
        exitButtonListener.buttonPressed();
    }

    @FXML
    void followers(ActionEvent event) throws IOException{
        followersButtonListener.buttonPressed();
    }

    @FXML
    void followings(ActionEvent event) throws IOException{
        followingsButtonListener.buttonPressed();
    }

    @FXML
    void myTweets(ActionEvent event) throws IOException{
        myTweetsButtonListener.buttonPressed();
    }

    @FXML
    void myInfo(ActionEvent event) throws IOException{
        myInfoButtonListener.buttonPressed();
    }

    @FXML
    void notifications(ActionEvent event) throws IOException{
        notificationsButtonListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButtonListener = new GoBackListener();
        exitButtonListener = new ExitProgramListener();
        notificationsButtonListener = new GoToNotificationsListener();
        followersButtonListener = new GoToFollowersListener();
        followingsButtonListener = new GoToFollowingsListener();
        blackListButtonListener = new GoToBlackListListener();
        myInfoButtonListener = new GoToMyInfoListener();
        myTweetsButtonListener = new GoToMyTweetsListener();
        createNewTweetButtonListener = new GoToCreateNewTweetListener();
        editInfoButtonListener = new GoToEditInfoListener();
    }
}
