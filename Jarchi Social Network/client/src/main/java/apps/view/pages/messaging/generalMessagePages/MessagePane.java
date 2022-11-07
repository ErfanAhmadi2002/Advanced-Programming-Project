package apps.view.pages.messaging.generalMessagePages;

import apps.listeners.messagingListeners.monoMessaging.LoadMessagePaneImageListener;
import apps.listeners.messagingListeners.monoMessaging.MessageShownListener;
import apps.listeners.messagingListeners.monoMessaging.RemoveMessageListener;
import apps.listeners.messagingListeners.pageChanger.GoToEditMessagePage;
import config.Config;
import controller.LogicalAgent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.Message;
import shared.models.MessageCopy;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessagePane extends Page implements Initializable {
    private MessageCopy message;
    private UserCopy writer;
    private PageListener goToEditMessagePage;
    private PageListener removeMessageListener;
    private PageListener messageShown;
    private PageListener loadMessagePaneImage;

    @FXML
    private ImageView image;

    @FXML
    private Text messageText;

    @FXML
    private Text authorText;

    @FXML
    private Text seenState;

    @FXML
    private Button edit;

    @FXML
    private Button remove;

    @FXML
    private Text forwardedText;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    @FXML
    void edit(ActionEvent event) {
        goToEditMessagePage.eventOccurred(this);
    }

    @FXML
    void remove(ActionEvent event) {
        removeMessageListener.eventOccurred(this);
    }

    public void setWriter(UserCopy writer) {
        this.writer = writer;
    }

    public void setMessage(MessageCopy message) {
        this.message = message;
    }

    public MessageCopy getMessage() {
        return message;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getRemove() {
        return remove;
    }


    public void startPage (MessageCopy message , ArrayList<UserCopy> usersInChat , boolean isGroupMessage) {
        UserCopy user1 = LogicalAgent.viewManager.getUser();
        setMessage(message);
        for (UserCopy member:usersInChat) {
            if (message.getSenderId() == member.getId()){
                setWriter(member);
                break;
            }
            else {
                messageShown.eventOccurred(this);
            }
        }
        if (!isGroupMessage) {
            String seenText = "";
            switch (message.getSeenState()) {
                case 1:
                    seenText = "*";
                    break;
                case 2:
                    seenText = "**";
                    break;
                case 3:
                    seenText = "***";
                    break;
                case 4:
                    seenText = "****";
                    break;
            }
            seenState.setText(seenText);
        }
        if (user1.getId() != writer.getId() || message.isForwarded()){
            edit.setVisible(false);
            remove.setVisible(false);
        }
        messageText.setText(message.getText());
        authorText.setText(writer.getUserName());
        if (message.isForwarded()) {
            Config config = Config.getConfig("general");
            forwardedText.setText(config.getProperty(String.class, "forward"));
        }
        loadMessagePaneImage.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goToEditMessagePage = new GoToEditMessagePage();
        removeMessageListener = new RemoveMessageListener();
        messageShown = new MessageShownListener();
        loadMessagePaneImage = new LoadMessagePaneImageListener();
    }

    public void setLoadedImage (Image image){
        this.image.setImage(image);
    }

}
