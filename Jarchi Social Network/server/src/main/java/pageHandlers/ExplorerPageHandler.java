package pageHandlers;

import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.explorerEvents.GetMainTweetsEvent;
import shared.events.explorerEvents.SearchAccountEvent;
import shared.models.*;
import shared.responses.Response;
import shared.responses.explorerResponses.GetMainTweetsResponse;
import shared.responses.explorerResponses.SearchAccountResponse;

import java.util.ArrayList;

public class ExplorerPageHandler extends PageHandler {
    static private final Logger logger = LogManager.getLogger(ExplorerPageHandler.class);

    public ExplorerPageHandler(ClientManager clientManager) {
        super(clientManager);
    }

    public Response getMainTweets(GetMainTweetsEvent getMainTweetsEvent) {
        if (clientManager.getAuthToken() == getMainTweetsEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(getMainTweetsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
            ArrayList<Integer> tweetsID = clientManager.getContext().tweetDataBase.getMainTweetsIdList();
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
            return new GetMainTweetsResponse(tweets);
        }
        return null;
    }

    public Response searchAccount(SearchAccountEvent searchAccountEvent) {
        if (clientManager.getAuthToken() == searchAccountEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(searchAccountEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                User requested = clientManager.getContext().userDataBase.Load(clientManager.getContext().previousData.getAllUserNames().get(searchAccountEvent.getUsername()));
                logger.info("the user : " + user.getUserName() + " requested to see th profile of the user : " + user.getUserName());
                return new SearchAccountResponse(new UserCopy(requested));
            } catch (Throwable throwable) {
                return null;
            }
        }
        return null;
    }
}
