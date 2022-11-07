package apps.view.pages.messaging.savedMessages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.pageChanger.GoToWriteMessageInSavedMessagesPage;
import apps.listeners.messagingListeners.startPages.StartSavedMessagesListener;
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

public class SavedMessages extends Page implements Initializable {
    private VBox vBox;
    private ArrayList<Tweet> savedMessages;
    private PageListener startSavedMessagesPage;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ButtonListener writeMessageInSavedMessageListener;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button writeMessage;

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
    void writeMessage(ActionEvent event)throws IOException{
        writeMessageInSavedMessageListener.buttonPressed();
    }

    public void setSavedMessages(ArrayList<Tweet> savedMessages) {
        this.savedMessages = savedMessages;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        startSavedMessagesPage = new StartSavedMessagesListener();
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.writeMessageInSavedMessageListener = new GoToWriteMessageInSavedMessagesPage();
        try {
            startSavedMessagesPage.eventOccurred(this);
            showSavedMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSavedMessages () throws IOException {
        for (Tweet tweet:savedMessages){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "savedMessagesPane")));
                AnchorPane pane = fxmlLoader.load();
                SavedMessagesPane controller = fxmlLoader.getController();
                controller.startPage(tweet);
                vBox.getChildren().add(pane);
            }catch (Throwable ignored){}
        }
    }
}
