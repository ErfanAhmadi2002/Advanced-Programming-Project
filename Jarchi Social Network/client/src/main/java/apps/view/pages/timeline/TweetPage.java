package apps.view.pages.timeline;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.profileListeners.GoToProfileListener;
import apps.listeners.timelineListeners.commentListeners.GoToForwardMessageListener;
import apps.listeners.timelineListeners.commentListeners.GoToWriteCommentListener;
import apps.listeners.timelineListeners.commentListeners.ShowCommentsListener;
import apps.listeners.timelineListeners.tweetCommandListeners.*;
import apps.listeners.timelineListeners.writerCommandListeners.BlockWriterListener;
import apps.listeners.timelineListeners.writerCommandListeners.MuteWriterListener;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.ButtonListener;
import listeners.PageListener;
import listeners.ProfileListener;
import shared.models.TweetCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TweetPage extends Page implements Initializable {
    private TweetCopy currentTweet;
    private ButtonListener backButtonListener;
    private ButtonListener exitButtonListener;
    private PageListener likeButtonListener;
    private PageListener saveButtonListener;
    private PageListener dislikeButtonListener;
    private PageListener resendButtonListener;
    private PageListener forwardButtonListener;
    private PageListener reportButtonListener;
    private PageListener muteButtonListener;
    private PageListener blockButtonListener;
    private PageListener goToWriteCommentListener;
    private PageListener showCommentsListener;
    private ProfileListener goToProfilePageListener;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Text tweetText;

    @FXML
    private ImageView image;

    @FXML
    private Text authorText;

    @FXML
    private Button like;

    @FXML
    private Button save;

    @FXML
    private Button resend;

    @FXML
    private Button forward;

    @FXML
    private Button block;

    @FXML
    private Button showProfile;

    @FXML
    private Button writeComment;

    @FXML
    private Button showComments;

    @FXML
    private Button dislike;

    @FXML
    private Button mute;

    @FXML
    private Button report;

    @FXML
    void back(ActionEvent event) throws IOException {
        backButtonListener.buttonPressed();
    }

    @FXML
    void block(ActionEvent event) throws IOException {
        blockButtonListener.eventOccurred(this);
    }

    @FXML
    void dislike(ActionEvent event) throws IOException {
        dislikeButtonListener.eventOccurred(this);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitButtonListener.buttonPressed();
    }

    @FXML
    void forward(ActionEvent event) throws IOException {
        forwardButtonListener.eventOccurred(this);
    }

    @FXML
    void like(ActionEvent event) throws IOException {
        likeButtonListener.eventOccurred(this);
    }

    @FXML
    void mute(ActionEvent event) throws IOException {
        muteButtonListener.eventOccurred(this);
    }

    @FXML
    void report(ActionEvent event) throws IOException {
        reportButtonListener.eventOccurred(this);
    }

    @FXML
    void resend(ActionEvent event) throws IOException {
        resendButtonListener.eventOccurred(this);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        saveButtonListener.eventOccurred(this);
    }

    @FXML
    void showComments(ActionEvent event) throws IOException {
        showCommentsListener.eventOccurred(this);
    }

    @FXML
    void showProfile(ActionEvent event) throws IOException {
        goToProfilePageListener.eventOccurred(currentTweet.getWriter());
    }

    @FXML
    void writeComment(ActionEvent event) throws IOException {
        goToWriteCommentListener.eventOccurred(this);
    }


    public TweetCopy getCurrentTweet() {
        return currentTweet;
    }

    public void setCurrentTweet(TweetCopy currentTweet) {
        this.currentTweet = currentTweet;
    }

    public void setTweetImage(Image image) {
        this.image.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButtonListener = new GoBackListener();
        exitButtonListener = new ExitProgramListener();
        likeButtonListener = new LikeTweetListener();
        dislikeButtonListener = new DislikeTweetListener();
        resendButtonListener = new ResendTweetListener();
        saveButtonListener = new SaveTweetListener();
        muteButtonListener = new MuteWriterListener();
        blockButtonListener = new BlockWriterListener();
        reportButtonListener = new ReportTweetListener();
        goToWriteCommentListener = new GoToWriteCommentListener();
        showCommentsListener = new ShowCommentsListener();
        goToProfilePageListener = new GoToProfileListener();
        forwardButtonListener = new GoToForwardMessageListener();
    }

    public void updateScene() {
        try {
            tweetText.setText(currentTweet.getText());
            authorText.setText(currentTweet.getWriter().getUserName());
            if (currentTweet.getImage() != null) {
                Image image = currentTweet.getImage().convertToImage();
                setTweetImage(image);
            }
        }catch (Throwable throwable){
            Config config = Config.getConfig("errorMessages");
            tweetText.setText(config.getProperty(String.class , "deleted"));
        }
    }

}
