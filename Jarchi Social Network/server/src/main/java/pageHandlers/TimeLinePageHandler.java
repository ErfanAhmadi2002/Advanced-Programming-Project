package pageHandlers;

import config.Config;
import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.timelineEvents.*;
import shared.models.*;
import shared.responses.Response;
import shared.responses.timelineResponses.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeLinePageHandler extends PageHandler{
    static private final Logger logger = LogManager.getLogger(TimeLinePageHandler.class);

    public TimeLinePageHandler(ClientManager clientManager) {
        super(clientManager);
    }


    public Response blockWriter(BlockWriterEvent blockWriterEvent){
        if (clientManager.getAuthToken() == blockWriterEvent.getAuthToken()){
            try {
                User user = clientManager.getOnlineUsers().get(blockWriterEvent.getAuthToken());
                try {
                    user = clientManager.getContext().userDataBase.Load(user.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                User writer = clientManager.getContext().userDataBase.Load(blockWriterEvent.getWriter().getId());
                if (!user.getBlackList().contains(writer.getId())) {
                    user.getBlackList().add(writer.getId());
                    logger.info("The User : " + user.getUserName() + " blocked the User : " + writer.getUserName());
                }
                clientManager.getContext().userDataBase.update(user);
            }catch (Throwable ignored){}
            return new BlockWriterResponse();
        }
        return null;
    }

    public Response forwardTweet(ForwardTweetEvent forwardTweetEvent){
        if (clientManager.getAuthToken() == forwardTweetEvent.getAuthToken()){
            try {
                Tweet currentTweet = clientManager.getContext().tweetDataBase.Load(forwardTweetEvent.getTweetId());
                User selected = clientManager.getContext().userDataBase.Load(clientManager.getContext().previousData.getAllUserNames().get(forwardTweetEvent.getSelectedName()));
                User user = clientManager.getOnlineUsers().get(forwardTweetEvent.getAuthToken());
                try {
                    user = clientManager.getContext().userDataBase.Load(user.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                if ((user.getFollowers().contains(selected.getId()) || user.getFollowings().contains(selected.getId())) &&
                        !user.getBlackList().contains(selected.getId()) && !selected.getBlackList().contains(user.getId())){
                    Message message = new Message(user.getId(), currentTweet.getText() , true , clientManager.getContext().messageDataBase.getLastId());
                    message.setImageId(currentTweet.getImageId());
                    if (!user.getAllMessages().containsKey(selected.getId())){
                        user.getAllMessages().put(selected.getId() , new ArrayList<>());
                        selected.getAllMessages().put(user.getId() , new ArrayList<>());
                    }
                    user.getAllMessages().get(selected.getId()).add(message.getId());
                    selected.getAllMessages().get(user.getId()).add(message.getId());
                    logger.info("The User : " + user.getUserName() + " forwarded the tweet with Id : " + forwardTweetEvent.getTweetId() + " to User : " + selected.getUserName());
                    clientManager.getContext().messageDataBase.update(message);
                    clientManager.getContext().userDataBase.update(user);
                    clientManager.getContext().userDataBase.update(selected);
                }
                return new ForwardTweetResponse(true);
            }catch (Throwable ignored){
                return new ForwardTweetResponse(false);
            }
        }
        return null;
    }

    public Response likeOrDislike(LikeOrDislikeTweetEvent likeOrDislikeTweetEvent){
        if (clientManager.getAuthToken() == likeOrDislikeTweetEvent.getAuthToken()){
            Tweet tweet = null;
            try {
                tweet = clientManager.getContext().tweetDataBase.Load(likeOrDislikeTweetEvent.getTweetId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert tweet!=null;
            User user = clientManager.getOnlineUsers().get(likeOrDislikeTweetEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if (likeOrDislikeTweetEvent.isLiked()) {
                if (!tweet.getUsersWhoLiked().contains(user.getId())) {
                    user.getFavoriteTweets().add(tweet.getId());
                    tweet.getUsersWhoLiked().add(user.getId());
                    tweet.setNumberOfLikes(tweet.getNumberOfLikes() + 1);
                    logger.info("the user : " + user.getUserName() + " liked the tweet with id : " + tweet.getId());
                }
            }else {
                if (!tweet.getUsersWhoDisliked().contains(user.getId())) {
                    tweet.getUsersWhoDisliked().add(user.getId());
                    tweet.setNumberOfLikes(tweet.getNumberOfLikes()-1);
                    logger.info("the user : " + user.getUserName() + " disliked the tweet with id : " + tweet.getId());
                }
            }
            try {
                clientManager.getContext().userDataBase.update(user);
                if (tweet.isPrimaryTweet()){
                    clientManager.getContext().tweetDataBase.update(tweet);
                }
                else {
                    clientManager.getContext().commentDataBase.update(tweet);
                }
            }catch (Throwable throwable){
                logger.error("the tweet file could not be rewrote");
            }
            return new LikeOrDislikeTweetResponse();
        }
        return null;
    }

    public Response loadComments (LoadCommentsEvent loadCommentsEvent){
        if (clientManager.getAuthToken() == loadCommentsEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(loadCommentsEvent.getAuthToken());
            ArrayList<Integer> tweetsID = new ArrayList<>();
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                Tweet tweet = clientManager.getContext().tweetDataBase.Load(loadCommentsEvent.getTweetIds());
                tweetsID = tweet.getCommentsId();
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
            ArrayList<TweetCopy> tweets = new ArrayList<>();
            for (Integer id:tweetsID) {
                try {
                    Tweet tweet = clientManager.getContext().tweetDataBase.Load(id);
                    User writer = clientManager.getContext().userDataBase.Load(tweet.getUserId());
                    MyImage myImage = null;
                    if (tweet.getImageId() != 0){
                        myImage = clientManager.getContext().imageDataBase.Load(tweet.getImageId());
                    }
                    if (writer.isActive() && !user.getMutedUsers().contains(writer.getId())){
                        if (writer.isPrivacyState() || writer.getFollowers().contains(user.getId())){
                            tweets.add(new TweetCopy(tweet , new UserCopy(writer) , myImage));
                        }
                    }
                }catch (Throwable ignored){}
            }
            return new LoadCommentsResponse(tweets);
        }
        return null;
    }

    public Response muteWriter (MuteWriterEvent muteWriterEvent){
        if (clientManager.getAuthToken() == muteWriterEvent.getAuthToken()){
            try {
                User user = clientManager.getOnlineUsers().get(muteWriterEvent.getAuthToken());
                try {
                    user = clientManager.getContext().userDataBase.Load(user.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                User writer = clientManager.getContext().userDataBase.Load(muteWriterEvent.getWriter().getId());
                if (!user.getMutedUsers().contains(writer.getId())) {
                    user.getMutedUsers().add(writer.getId());
                    logger.info("The User : " + user.getUserName() + " muted the User : " + writer.getUserName());
                }
                clientManager.getContext().userDataBase.update(user);
            }catch (Throwable ignored){}
            return new MuteWriterResponse();
        }
        return null;
    }

    public Response reportTweet (ReportTweetEvent reportTweetEvent){
        if (clientManager.getAuthToken() == reportTweetEvent.getAuthToken()){
            try {
                User user = clientManager.getOnlineUsers().get(reportTweetEvent.getAuthToken());
                try {
                    user = clientManager.getContext().userDataBase.Load(user.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                HashMap<Integer, Integer> reportList = clientManager.getContext().previousData.getReportList();
                int tweetId = reportTweetEvent.getTweetId();
                if (reportList.containsKey(tweetId)) {
                    reportList.put(tweetId, reportList.get(tweetId) + 1);
                } else {
                    reportList.put(tweetId, 1);
                }
                logger.info("The User : " + user.getUserName() + " Reported the tweet with id : " + tweetId);
                boolean isDeleted = checkReportNumbers(reportList, tweetId);
                clientManager.getContext().previousData.saveReportList(reportList);
                return new ReportTweetResponse(isDeleted);
            }catch (IOException exception){
                logger.error("reportList data file could not be Loaded ");
            }
        }
        return null;
    }

    private boolean checkReportNumbers (HashMap<Integer , Integer> reportList , int tweetId) {
        Config config = Config.getConfig("general");
        if (reportList.get(tweetId).equals(config.getProperty(Integer.class, "maxReport"))) {
            clientManager.getContext().tweetDataBase.delete(tweetId);
            reportList.remove(tweetId);
            try {
                clientManager.getContext().previousData.saveReportList(reportList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return true;
        }return false;
    }

    public Response resendTweet (ResendTweetEvent resendTweetEvent){
        if (clientManager.getAuthToken() == resendTweetEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(resendTweetEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.getMyTweets().add(resendTweetEvent.getTweetId());
            user.getFavoriteTweets().add(resendTweetEvent.getTweetId());
            logger.info("the user : " + user.getUserName() + " resent the tweet with id : " + resendTweetEvent.getTweetId());
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResendTweetResponse();
        }
        return null;
    }

    public Response saveTweet (SaveTweetEvent saveTweetEvent){
        if (clientManager.getAuthToken() == saveTweetEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(saveTweetEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.getSavedTweets().add(saveTweetEvent.getTweetId());
            logger.info("the user : " + user.getUserName() + " saved the tweet with id : " + saveTweetEvent.getTweetId());
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new SaveTweetResponse();
        }
        return null;
    }

    public Response getFirstTweetSeries (GetFirstTweetSeriesEvent getFirstTweetSeriesEvent){
        if (clientManager.getAuthToken() == getFirstTweetSeriesEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(getFirstTweetSeriesEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
            ArrayList<Integer> tweetsID = clientManager.getOnlineUsers().get(getFirstTweetSeriesEvent.getAuthToken()).getFavoriteTweets();
            ArrayList<TweetCopy> tweets = new ArrayList<>();
            for (Integer id:tweetsID) {
                try {
                    Tweet tweet = clientManager.getContext().tweetDataBase.Load(id);
                    User writer = clientManager.getContext().userDataBase.Load(tweet.getUserId());
                    MyImage myImage = null;
                    if (tweet.getImageId() != 0){
                        myImage = clientManager.getContext().imageDataBase.Load(tweet.getImageId());
                    }
                    if (writer.isActive() && !user.getMutedUsers().contains(writer.getId())){
                        if (writer.isPrivacyState() || writer.getFollowers().contains(user.getId())){
                            tweets.add(new TweetCopy(tweet , new UserCopy(writer) , myImage));
                        }
                    }
                }catch (Throwable ignored){}
            }
            return new GetFirstTweetSeriesResponse(tweets);
        }
        return null;
    }

    public Response writeComment (WriteCommentEvent writeCommentEvent){
        if (clientManager.getAuthToken() == writeCommentEvent.getAuthToken()){
            if (!writeCommentEvent.getNewCommentFormEvent().getCommentText().equals("")) {
                try {
                    User user = clientManager.getOnlineUsers().get(writeCommentEvent.getAuthToken());
                    try {
                        user = clientManager.getContext().userDataBase.Load(user.getId());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    int lastId = clientManager.getContext().commentDataBase.getLastId();
                    Tweet comment = new Tweet(writeCommentEvent.getNewCommentFormEvent().getCommentText(), user.getId(), lastId, false);
                    Tweet currentTweet = clientManager.getContext().tweetDataBase.Load(writeCommentEvent.getTweetId());
                    currentTweet.getCommentsId().add(comment.getId());
                    logger.info("The User : " + user.getUserName() + " give comment with Id : " + comment.getId() + " to the tweet with Id : " + currentTweet.getId());
                    clientManager.getContext().commentDataBase.update(comment);
                    if (currentTweet.isPrimaryTweet()) {
                        clientManager.getContext().tweetDataBase.update(currentTweet);
                    } else {
                        clientManager.getContext().commentDataBase.update(currentTweet);
                    }
                }catch (Throwable throwable){
                    logger.error("The tweet file was not found");
                }
                return new WriteCommentResponse();
            }
        }
        return null;
    }

}
