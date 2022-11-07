package apps.view.pages.timeline;

import apps.listeners.timelineListeners.iteratorListeners.GoToTweetPageListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.TweetCopy;
import view.Page;

import java.net.URL;
import java.util.ResourceBundle;


public class TweetPane extends Page implements Initializable {
    private TweetCopy tweetCopy;
    private PageListener goToTweetPageListener;

    @FXML
    private ImageView image;

    @FXML
    private Text tweetText;

    @FXML
    private Text authorText;

    @FXML
    private Button details;


    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TweetCopy getTweetCopy() {
        return tweetCopy;
    }

    public void setTweetCopy(TweetCopy tweetCopy) {
        this.tweetCopy = tweetCopy;
    }

    @FXML
    void details(ActionEvent event) {
        goToTweetPageListener.eventOccurred(this);
    }

    public void startPage(TweetCopy tweetCopy) {
        this.tweetCopy = tweetCopy;
        tweetText.setText(tweetCopy.getText());
        authorText.setText(tweetCopy.getWriter().getUserName());
        if (tweetCopy.getImage() != null) {
            Image image = tweetCopy.getImage().convertToImage();
            this.image.setImage(image);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.goToTweetPageListener = new GoToTweetPageListener();
    }
}
