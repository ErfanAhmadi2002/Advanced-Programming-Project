package apps.view.pages.personal.myTweets;

import apps.listeners.personalListeners.myTweets.LoadImageTweetPaneListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.Tweet;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TweetPane extends Page implements Initializable {
    private Tweet tweet;
    private PageListener loadImageTweetPane;

    @FXML
    private ImageView image;

    @FXML
    private Text tweetText;

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void startPage(Tweet tweet) throws IOException {
        setTweet(tweet);
        tweetText.setText(tweet.getText());
        loadImageTweetPane.eventOccurred(this);
    }

    public void setLoadedImage (Image image){
        this.image.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadImageTweetPane = new LoadImageTweetPaneListener();
    }
}
