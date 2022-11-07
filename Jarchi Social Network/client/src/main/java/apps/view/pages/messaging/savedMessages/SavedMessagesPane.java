package apps.view.pages.messaging.savedMessages;

import apps.listeners.messagingListeners.startPages.StartSavedMessagesPaneListener;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.Tweet;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;


public class SavedMessagesPane extends Page {
    private Tweet tweet;
    private UserCopy writer;
    private PageListener startSavedMessagePane;

    @FXML
    private Text tweetText;

    @FXML
    private Text authorText;

    @FXML
    private ImageView image;


    public void setWriter(UserCopy writer) {
        this.writer = writer;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void startPage (Tweet tweet) throws IOException {
        setTweet(tweet);
        startSavedMessagePane = new StartSavedMessagesPaneListener();
        startSavedMessagePane.eventOccurred(this);
        authorText.setText(writer.getUserName());
        tweetText.setText(tweet.getText());
    }

    public void setLoadedImage(Image image){this.image.setImage(image);}
}
