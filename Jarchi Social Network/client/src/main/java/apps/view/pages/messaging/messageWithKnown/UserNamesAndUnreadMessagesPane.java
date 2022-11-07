package apps.view.pages.messaging.messageWithKnown;

import apps.listeners.messagingListeners.monoMessaging.CalculateUnreadMessagesListener;
import apps.listeners.messagingListeners.monoMessaging.ShowMessageListForKnownUsersListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.Message;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserNamesAndUnreadMessagesPane extends Page implements Initializable {
    private UserCopy selected;
    private ArrayList<Message> messages;
    private int unreadMessages;
    private PageListener calculateUnreadMessages;
    private PageListener showButtonListener;


    @FXML
    private Text info;

    @FXML
    private Button show;

    public void setSelected(UserCopy selected) {
        this.selected = selected;
    }

    public void setUnreadMessages(int unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public UserCopy getSelected() {
        return selected;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @FXML
    void show(ActionEvent event) {
        showButtonListener.eventOccurred(this);
    }

    public void startPage (UserCopy selected , ArrayList<Message> messages) {
        setSelected(selected);
        setMessages(messages);
        calculateUnreadMessages.eventOccurred(this);
        info.setText(selected.getUserName() + " with " + unreadMessages + " unread Messages");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calculateUnreadMessages = new CalculateUnreadMessagesListener();
        showButtonListener = new ShowMessageListForKnownUsersListener();
    }
}
