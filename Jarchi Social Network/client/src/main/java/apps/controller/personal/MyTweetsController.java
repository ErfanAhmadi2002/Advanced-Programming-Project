package apps.controller.personal;

import apps.view.pages.personal.CreateNewTweet;
import apps.view.pages.personal.myTweets.MyTweets;
import apps.view.pages.personal.myTweets.TweetPane;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.generalEvents.LoadImageEvent;
import shared.events.personalEvents.LoadMyTweetsEvent;
import shared.events.personalEvents.WriteNewTweetEvent;
import shared.models.Tweet;
import shared.responses.generalResponses.LoadImageResponse;
import shared.responses.personalResponses.LoadMyTweetsResponse;
import shared.responses.personalResponses.WriteNewTweetResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyTweetsController extends Controller {

    public MyTweetsController() {
    }

    public void startMyTweetsPage (MyTweets page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadMyTweetsEvent loadMyTweetsEvent = new LoadMyTweetsEvent(LogicalAgent.authToken);
        LoadMyTweetsResponse loadMyTweetsResponse = (LoadMyTweetsResponse) socketEventSender.request(loadMyTweetsEvent);
        page.setMyTweets(loadMyTweetsResponse.getMyTweets());
    }

    public void writeNewTweet (CreateNewTweet page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        String tweetText = page.getTweetText().getText();
        if (!tweetText.equals("")){
            WriteNewTweetEvent writeNewTweetEvent;
            if (page.getImageFile() != null){
                File imageFile = page.getImageFile();
                byte[] bytes = new byte[0];
                try {
                    bytes = Files.readAllBytes(imageFile.toPath());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                writeNewTweetEvent = new WriteNewTweetEvent(LogicalAgent.authToken , tweetText , bytes);
            }else {
                writeNewTweetEvent = new WriteNewTweetEvent(LogicalAgent.authToken , tweetText , null);
            }
            WriteNewTweetResponse writeNewTweetResponse = (WriteNewTweetResponse) socketEventSender.request(writeNewTweetEvent);
        }
    }

    public void loadTweetImage (TweetPane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        Tweet tweet = pane.getTweet();
        if (tweet.getImageId() != 0){
            LoadImageEvent loadImageEvent = new LoadImageEvent(LogicalAgent.authToken , tweet.getImageId());
            LoadImageResponse loadImageResponse = (LoadImageResponse) socketEventSender.request(loadImageEvent);
            pane.setLoadedImage(loadImageResponse.getImage().convertToImage());
        }
    }
}
