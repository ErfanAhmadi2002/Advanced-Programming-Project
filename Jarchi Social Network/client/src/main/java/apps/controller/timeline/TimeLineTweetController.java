package apps.controller.timeline;

import apps.view.pages.timeline.ForwardMessage;
import apps.view.pages.timeline.TweetPage;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.timelineEvents.*;
import shared.responses.timelineResponses.*;


public class TimeLineTweetController extends Controller {

    public TimeLineTweetController() {

    }

    public void likeTweet (TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LikeOrDislikeTweetEvent likeOrDislikeTweetEvent = new LikeOrDislikeTweetEvent(LogicalAgent.authToken , true , page.getCurrentTweet().getId());
        LikeOrDislikeTweetResponse likeOrDislikeTweetResponse = (LikeOrDislikeTweetResponse) socketEventSender.request(likeOrDislikeTweetEvent);
    }

    public void dislikeTweet (TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LikeOrDislikeTweetEvent likeOrDislikeTweetEvent = new LikeOrDislikeTweetEvent(LogicalAgent.authToken , false , page.getCurrentTweet().getId());
        LikeOrDislikeTweetResponse likeOrDislikeTweetResponse = (LikeOrDislikeTweetResponse) socketEventSender.request(likeOrDislikeTweetEvent);
    }

    public void resendTweet(TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        ResendTweetEvent resendTweetEvent = new ResendTweetEvent(LogicalAgent.authToken , page.getCurrentTweet().getId());
        ResendTweetResponse resendTweetResponse = (ResendTweetResponse) socketEventSender.request(resendTweetEvent);
    }

    public void saveTweet(TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        SaveTweetEvent saveTweetEvent = new SaveTweetEvent(LogicalAgent.authToken , page.getCurrentTweet().getId());
        SaveTweetResponse saveTweetResponse = (SaveTweetResponse) socketEventSender.request(saveTweetEvent);
    }

    public void reportTweet (TweetPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        ReportTweetEvent reportTweetEvent = new ReportTweetEvent(LogicalAgent.authToken , page.getCurrentTweet().getId());
        ReportTweetResponse reportTweetResponse = (ReportTweetResponse) socketEventSender.request(reportTweetEvent);
        if (reportTweetResponse.isDeleted()){
            page.setCurrentTweet(null);
            page.updateScene();
        }
    }

    public void forwardTweet (ForwardMessage page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        ForwardTweetEvent forwardTweetEvent = new ForwardTweetEvent(LogicalAgent.authToken , page.getCurrentTweet().getId() , page.getUsername().getText());
        ForwardTweetResponse forwardTweetResponse = (ForwardTweetResponse) socketEventSender.request(forwardTweetEvent);
        page.showResult(forwardTweetResponse.isSuccessful());
    }

}
