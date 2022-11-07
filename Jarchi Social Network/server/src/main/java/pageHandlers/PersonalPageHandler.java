package pageHandlers;

import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.personalEvents.*;
import shared.events.personalEvents.notification.*;
import shared.models.MyImage;
import shared.models.Tweet;
import shared.models.User;
import shared.models.UserCopy;
import shared.responses.Response;
import shared.responses.personalResponses.*;
import shared.responses.personalResponses.notification.*;

import java.io.IOException;
import java.util.ArrayList;

public class PersonalPageHandler extends PageHandler{
    static private final Logger logger = LogManager.getLogger(PersonalPageHandler.class);

    public PersonalPageHandler(ClientManager clientManager) {
        super(clientManager);
    }

    public Response loadBlackList(LoadBlacklistEvent loadBlacklistEvent){
        if (clientManager.getAuthToken() == loadBlacklistEvent.getAuthToken()){
            ArrayList<UserCopy> copyBlackList = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadBlacklistEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer userId:user.getBlackList()) {
                try {
                    User blockedUser = clientManager.getContext().userDataBase.Load(userId);
                    copyBlackList.add(new UserCopy(blockedUser));
                }catch (Throwable ignored){}
            }
            return new LoadBlacklistResponse(copyBlackList);
        }
        return null;
    }

    public Response loadFollowers(LoadFollowersEvent loadFollowersEvent){
        if (clientManager.getAuthToken() == loadFollowersEvent.getAuthToken()){
            ArrayList<UserCopy> copyFollowers = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadFollowersEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer userId:user.getFollowers()) {
                try {
                    User follower = clientManager.getContext().userDataBase.Load(userId);
                    copyFollowers.add(new UserCopy(follower));
                }catch (Throwable ignored){}
            }
            return new LoadFollowersResponse(copyFollowers);
        }
        return null;
    }

    public Response loadFollowings(LoadFollowingsEvent loadFollowingsEvent){
        if (clientManager.getAuthToken() == loadFollowingsEvent.getAuthToken()){
            ArrayList<UserCopy> copyFollowings = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadFollowingsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer userId:user.getFollowings()) {
                try {
                    User following = clientManager.getContext().userDataBase.Load(userId);
                    copyFollowings.add(new UserCopy(following));
                }catch (Throwable ignored){}
            }
            return new LoadFollowingsResponse(copyFollowings);
        }
        return null;
    }

    public Response changeFirstName(ChangeFirstNameEvent changeFirstNameEvent){
        if (clientManager.getAuthToken() == changeFirstNameEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(changeFirstNameEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.setFirstName(changeFirstNameEvent.getNewNameFormEvent().getNewName());
            logger.info("The user : " + user.getUserName() + " Changed the First Name");
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ChangeFirstNameResponse();
        }
        return null;
    }

    public Response changeLastName(ChangeLastNameEvent changeLastNameEvent){
        if (clientManager.getAuthToken() == changeLastNameEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(changeLastNameEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            user.setLastName(changeLastNameEvent.getNewNameFormEvent().getNewName());
            logger.info("The user : " + user.getUserName() + " Changed the Last Name");
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ChangeLastNameResponse();
        }
        return null;
    }

    public Response unfollow (UnfollowUserEvent unfollowUserEvent){
        if (clientManager.getAuthToken() == unfollowUserEvent.getAuthToken()){
            UserCopy userCopy = unfollowUserEvent.getUser();
            try {
                User user = clientManager.getContext().userDataBase.Load(userCopy.getId());
                User requested = clientManager.getOnlineUsers().get(unfollowUserEvent.getAuthToken());
                try {
                    requested = clientManager.getContext().userDataBase.Load(requested.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                requested.getFollowings().remove(Integer.valueOf(user.getId()));
                user.getFollowers().remove(Integer.valueOf(requested.getId()));
                logger.info("The user : " + requested.getUserName() + " Unfollowed the user : " + user.getUserName());
                if (!user.getNotifications().getRequestedToFollowMe().contains(requested.getId())) {
                    user.getNotifications().getFollowOrUnfollow().put(requested.getId(), false);
                }else {user.getNotifications().getRequestedToFollowMe().remove(Integer.valueOf(requested.getId()));}
                clientManager.getContext().userDataBase.update(requested);
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new UnfollowUserResponse();
        }
        return null;
    }

    public Response unblock (UnblockUserEvent unblockUserEvent){
        if (clientManager.getAuthToken() == unblockUserEvent.getAuthToken()){
            UserCopy userCopy = unblockUserEvent.getUser();
            try {
                User user = clientManager.getContext().userDataBase.Load(userCopy.getId());
                User requested = clientManager.getOnlineUsers().get(unblockUserEvent.getAuthToken());
                try {
                    requested = clientManager.getContext().userDataBase.Load(requested.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                requested.getBlackList().remove(Integer.valueOf(user.getId()));
                logger.info("The user : " + requested.getUserName() + " Unblocked the user : " + user.getUserName());
                clientManager.getContext().userDataBase.update(requested);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new UnblockUserResponse();
        }
        return null;
    }

    public Response loadMyTweets (LoadMyTweetsEvent loadMyTweetsEvent){
        if (clientManager.getAuthToken() == loadMyTweetsEvent.getAuthToken()){
            ArrayList<Tweet> myTweets = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadMyTweetsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer tweetId:user.getMyTweets()) {
                try {
                    Tweet tweet = clientManager.getContext().tweetDataBase.Load(tweetId);
                    myTweets.add(tweet);
                }catch (Throwable ignored){}
            }
            return new LoadMyTweetsResponse(myTweets);
        }
        return null;
    }

    public Response loadMyRequests(LoadMyRequestsEvent loadMyRequestsEvent) {
        if (clientManager.getAuthToken() == loadMyRequestsEvent.getAuthToken()){
            ArrayList<ItemNotification1> myRequests  = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadMyRequestsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer userId:user.getNotifications().getRequestedToFollowThem().keySet()) {
                try {
                    User user1 = clientManager.getContext().userDataBase.Load(userId);
                    myRequests.add(new ItemNotification1(new UserCopy(user1) , user.getNotifications().getRequestedToFollowThem().get(userId)));
                }catch (Throwable ignored){}
            }
            return new LoadMyRequestsResponse(myRequests);
        }
        return null;
    }

    public Response loadNewRequests(LoadNewRequestsEvent loadNewRequestsEvent) {
        if (clientManager.getAuthToken() == loadNewRequestsEvent.getAuthToken()){
            ArrayList<UserCopy> newRequests = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadNewRequestsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer userId:user.getNotifications().getRequestedToFollowMe()) {
                try {
                    User user1 = clientManager.getContext().userDataBase.Load(userId);
                    newRequests.add(new UserCopy(user1));
                }catch (Throwable ignored){}
            }
            return new LoadNewRequestsResponse(newRequests);
        }
        return null;
    }

    public Response loadFollowingState(LoadFollowingStateEvent loadFollowingStateEvent) {
        if (clientManager.getAuthToken() == loadFollowingStateEvent.getAuthToken()){
            ArrayList<ItemNotification2> followOrUnfollow  = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(loadFollowingStateEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer userId:user.getNotifications().getFollowOrUnfollow().keySet()) {
                try {
                    User user1 = clientManager.getContext().userDataBase.Load(userId);
                    followOrUnfollow.add(new ItemNotification2(new UserCopy(user1) , user.getNotifications().getFollowOrUnfollow().get(userId)));
                }catch (Throwable ignored){}
            }
            return new LoadFollowingStateResponse(followOrUnfollow);
        }
        return null;
    }

    public Response answerRequest(AnswerRequestEvent answerRequestEvent) {
        if (clientManager.getAuthToken() == answerRequestEvent.getAuthToken()){
            try {
                User user = clientManager.getOnlineUsers().get(answerRequestEvent.getAuthToken());
                try {
                    user = clientManager.getContext().userDataBase.Load(user.getId());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                User requested = clientManager.getContext().userDataBase.Load(answerRequestEvent.getRequested().getId());
                switch (answerRequestEvent.getAnswerType()){
                    case 1:
                        if (!requested.getNotifications().getRequestedToFollowThem().containsKey(user.getId())) {
                            requested.getNotifications().getRequestedToFollowThem().put(user.getId(), 2);
                            user.getNotifications().getRequestedToFollowMe().remove(Integer.valueOf(requested.getId()));
                            user.getFollowers().add(requested.getId());
                            logger.info("The user : " + user.getUserName() + " Accepted the following request of the user : " + requested.getUserName());
                            requested.getFollowings().add(user.getId());
                        }
                        break;
                    case 2:
                        if (!requested.getNotifications().getRequestedToFollowThem().containsKey(user.getId())) {
                            requested.getNotifications().getRequestedToFollowThem().put(user.getId(), 1);
                            user.getNotifications().getRequestedToFollowMe().remove(Integer.valueOf(requested.getId()));
                            logger.info("The user : " + user.getUserName() + " Rejected the following request of the user : " + requested.getUserName());
                        }
                        break;
                    case 3:
                        user.getNotifications().getRequestedToFollowMe().remove(Integer.valueOf(requested.getId()));
                        logger.info("The user : " + user.getUserName() + " safe rejected the following request of the user : " + requested.getUserName());
                        break;
                }
                clientManager.getContext().userDataBase.update(user);
                clientManager.getContext().userDataBase.update(requested);
                return new AnswerRequestResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Response deleteSeenNotifications(DeleteSeenNotificationsEvent deleteSeenNotificationsEvent) {
        if (clientManager.getAuthToken() == deleteSeenNotificationsEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(deleteSeenNotificationsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if (deleteSeenNotificationsEvent.getNotificationType() == 1){
                user.getNotifications().getRequestedToFollowThem().clear();
            }else {
                user.getNotifications().getFollowOrUnfollow().clear();
            }
            try {
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new DeleteSeenNotificationResponse();
        }
        return null;
    }

    public Response uploadProfileImage (UploadProfileImageEvent uploadProfileImageEvent){
        if (clientManager.getAuthToken() == uploadProfileImageEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(uploadProfileImageEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                MyImage myImage = new MyImage(uploadProfileImageEvent.getImage() , clientManager.getContext().imageDataBase.getLastId()+1);
                user.setImageId(myImage.getId());
                logger.info("The user : " + user.getUserName() + " uploaded profile image with id : " + myImage.getId());
                clientManager.getContext().imageDataBase.update(myImage);
                clientManager.getContext().userDataBase.update(user);
                return new UploadProfileImageResponse();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response writeNewTweet (WriteNewTweetEvent writeNewTweetEvent){
        if (clientManager.getAuthToken() == writeNewTweetEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(writeNewTweetEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String tweetText = writeNewTweetEvent.getText();
                if (!tweetText.equals("")){
                    Tweet tweet = new Tweet(tweetText , user.getId() , clientManager.getContext().tweetDataBase.getLastId() , true);
                    user.getMyTweets().add(tweet.getId());
                    logger.info("The user : " + user.getUserName() + " submitted a new tweet with id : " + tweet.getId());
                    if (writeNewTweetEvent.getImage() != null){
                        MyImage myImage = new MyImage(writeNewTweetEvent.getImage() , clientManager.getContext().imageDataBase.getLastId()+1);
                        tweet.setImageId(myImage.getId());
                        clientManager.getContext().imageDataBase.update(myImage);
                    }
                    clientManager.getContext().userDataBase.update(user);
                    clientManager.getContext().tweetDataBase.update(tweet);
                    return new WriteNewTweetResponse();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return new WriteNewTweetResponse();
        }
        return null;
    }
}
