package apps.view.pages.messaging.multipleMessaging;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.multipleMessaging.SendMessageToAllListener;
import apps.listeners.messagingListeners.multipleMessaging.StartMultipleMessagingListener;
import apps.listeners.messagingListeners.pageChanger.GoToCreateNewCategoryPage;
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
import shared.models.User;
import shared.models.UserCopy;
import view.Page;
import view.ViewManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MultipleMessaging extends Page implements Initializable {
    private VBox vBox;
    private HashMap<String , ArrayList<UserCopy>> allCategories;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener startMultipleMessagingListener;
    private ButtonListener createNewCategoryListener;
    private PageListener sendToAllListener;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button createNewCategory;

    @FXML
    private Button sendToAll;


    public void setAllCategories(HashMap<String, ArrayList<UserCopy>> allCategories) {
        this.allCategories = allCategories;
    }

    @FXML
    void createNewCategory(ActionEvent event) throws IOException {
        createNewCategoryListener.buttonPressed();
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
    void sendToAll(ActionEvent event) throws IOException {
        sendToAllListener.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox = new VBox();
        scrollPane.setContent(vBox);
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        createNewCategoryListener = new GoToCreateNewCategoryPage();
        sendToAllListener = new SendMessageToAllListener();
        startMultipleMessagingListener = new StartMultipleMessagingListener();

        try {
            startMultipleMessagingListener.eventOccurred(this);
            showCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCategories () throws IOException {
        for (String name:allCategories.keySet()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "categoryPane")));
                AnchorPane pane = fxmlLoader.load();
                CategoryPane controller = fxmlLoader.getController();
                controller.startPage(name , allCategories.get(name));
                vBox.getChildren().add(pane);
            }catch (Throwable ignored){}
        }
    }
}
