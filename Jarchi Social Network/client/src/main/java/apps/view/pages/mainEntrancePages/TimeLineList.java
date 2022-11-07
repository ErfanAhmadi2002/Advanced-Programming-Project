package apps.view.pages.mainEntrancePages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.view.pages.timeline.TweetPane;
import controller.LogicalAgent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import listeners.ButtonListener;
import shared.models.TweetCopy;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimeLineList extends Page implements Initializable {
    private ArrayList<TweetCopy> tweetList;
    private VBox vBox;
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

    public void setTweetList(ArrayList<TweetCopy> tweetList) {
        this.tweetList = tweetList;
    }

    public ArrayList<TweetCopy> getTweetList() {
        return tweetList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
    }

    public void showTheList () {
        Platform.runLater(() -> {
            vBox.getChildren().clear();
            for (TweetCopy tweetCopy:tweetList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "tweetPaneTimeLine")));
                AnchorPane pane = null;
                try {
                    pane = fxmlLoader.load();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                TweetPane controller = fxmlLoader.getController();
                controller.startPage(tweetCopy);
                vBox.getChildren().add(pane);
            }
        });

    }
}
