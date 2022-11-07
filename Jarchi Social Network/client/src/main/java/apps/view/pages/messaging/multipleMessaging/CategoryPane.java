package apps.view.pages.messaging.multipleMessaging;

import apps.listeners.messagingListeners.multipleMessaging.RemoveCategoryListener;
import apps.listeners.messagingListeners.multipleMessaging.SendMessageToCategoryListener;
import apps.listeners.messagingListeners.pageChanger.GoToEditCategoryPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoryPane extends Page implements Initializable {
    private String name;
    private ArrayList<UserCopy> users;
    private PageListener editButtonListener;
    private PageListener sendButtonListener;
    private PageListener removeButtonListener;

    @FXML
    private Text categoryName;

    @FXML
    private Button edit;

    @FXML
    private Button send;

    @FXML
    private Button remove;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsers(ArrayList<UserCopy> users) {
        this.users = users;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getSend() {
        return send;
    }

    public Button getRemove() {
        return remove;
    }

    public ArrayList<UserCopy> getUsers() {
        return users;
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        editButtonListener.eventOccurred(this);
    }

    @FXML
    void remove(ActionEvent event) throws IOException {
        removeButtonListener.eventOccurred(this);
    }

    @FXML
    void send(ActionEvent event) throws IOException {
        sendButtonListener.eventOccurred(this);
    }

    public void startPage(String name , ArrayList<UserCopy> users){
        setName(name);
        setUsers(users);
        categoryName.setText(name);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendButtonListener = new SendMessageToCategoryListener();
        removeButtonListener = new RemoveCategoryListener();
        editButtonListener = new GoToEditCategoryPage();
    }

    public void categoryRemoved (){
        this.getEdit().setVisible(false);
        this.getRemove().setVisible(false);
        this.getSend().setVisible(false);
    }
}
