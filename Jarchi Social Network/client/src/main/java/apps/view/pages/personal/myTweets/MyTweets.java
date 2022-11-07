package apps.view.pages.personal.myTweets;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.personalListeners.myTweets.StartMyTweetsPage;
import controller.LogicalAgent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.Tweet;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyTweets extends Page implements Initializable {
    private VBox vBox;
    private ArrayList<Tweet> myTweets;
    private PageListener startTweetPage;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button back;

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

    public void setMyTweets(ArrayList<Tweet> myTweets) {
        this.myTweets = myTweets;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        startTweetPage = new StartMyTweetsPage();
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        try {
            startTweetPage.eventOccurred(this);
            showTweets();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTweets () throws IOException {
        for (Tweet tweet:myTweets){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "tweetPane")));
            AnchorPane pane = fxmlLoader.load();
            TweetPane controller = fxmlLoader.getController();
            controller.startPage(tweet);
            vBox.getChildren().add(pane);
        }
    }
}
