package pageHandlers;

import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.generalEvents.*;
import shared.models.MyImage;
import shared.models.User;
import shared.responses.Response;
import shared.responses.generalResponses.*;

import java.io.IOException;


public class GeneralPageHandler extends PageHandler {
    static private final Logger logger = LogManager.getLogger(GeneralPageHandler.class);

    public GeneralPageHandler(ClientManager clientManager) {
        super(clientManager);
    }

    public Response loadImage(LoadImageEvent loadImageEvent) {
        if (clientManager.getAuthToken() == loadImageEvent.getAuthToken()) {
            try {
                if (loadImageEvent.getImageId() != 0) {
                    MyImage image = clientManager.getContext().imageDataBase.Load(loadImageEvent.getImageId());
                    return new LoadImageResponse(image);
                } else return new LoadImageResponse(null);
            } catch (Throwable throwable) {
                logger.warn("the image with id : " + loadImageEvent.getImageId() + " ");
            }
            return new LoadImageResponse(null);
        }
        return null;
    }

    public Response blockUser(BlockUserEvent blockUserEvent) {
        if (clientManager.getAuthToken() == blockUserEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(blockUserEvent.getAuthToken());
            try {
                boolean state = false;
                user = clientManager.getContext().userDataBase.Load(user.getId());
                User selected = clientManager.getContext().userDataBase.Load(blockUserEvent.getUserCopy().getId());
                if (!user.getBlackList().contains(selected.getId())){
                    user.getBlackList().add(selected.getId());
                    logger.info("the user : " + user.getUserName() + " blocked the user : " + selected.getUserName());
                }
                else {
                    Integer selectedId = selected.getId();
                    user.getBlackList().remove(selectedId);
                    logger.info("the user : " + user.getUserName() + " unblocked the user : " + selected.getUserName());
                    state = true;
                }
                clientManager.getContext().userDataBase.update(user);
                return new BlockUserResponse(state);
            }catch (Throwable ignored){
                return new BlockUserResponse(false);
            }
        }
        return null;
    }

    public Response followUser(FollowUserEvent followUserEvent) {
        if (clientManager.getAuthToken() == followUserEvent.getAuthToken()){
            User requested = clientManager.getOnlineUsers().get(followUserEvent.getAuthToken());
            try {
                boolean state = false;
                requested = clientManager.getContext().userDataBase.Load(requested.getId());
                User user = clientManager.getContext().userDataBase.Load(followUserEvent.getUserCopy().getId());
                if (!requested.getFollowings().contains(user.getId())) {
                    if (user.isPrivacyState()) {
                        requested.getFollowings().add(user.getId());
                        user.getFollowers().add(requested.getId());
                        logger.info("the user : " + requested.getUserName() + " followed the user : " + user.getUserName());
                        user.getNotifications().getFollowOrUnfollow().put(requested.getId(), true);
                        state = false;
                    } else {
                        if (!user.getNotifications().getRequestedToFollowMe().contains(requested.getId())) {
                            user.getNotifications().getRequestedToFollowMe().add(requested.getId());
                        }
                    }
                } else {
                    requested.getFollowings().remove(Integer.valueOf(user.getId()));
                    user.getFollowers().remove(Integer.valueOf(requested.getId()));
                    logger.info("the user : " + requested.getUserName() + " unfollowed the user : " + user.getUserName());
                    if (!user.getNotifications().getRequestedToFollowMe().contains(requested.getId())) {
                        user.getNotifications().getFollowOrUnfollow().put(requested.getId(), false);
                    } else {
                        user.getNotifications().getRequestedToFollowMe().remove(Integer.valueOf(requested.getId()));
                    }
                    state = true;
                }
                clientManager.getContext().userDataBase.update(user);
                clientManager.getContext().userDataBase.update(requested);
                return new FollowUserResponse(state);
            }catch (Throwable throwable){
                return new FollowUserResponse(false);
            }
        }
        return null;
    }

    public Response muteUser(MuteUserEvent muteUserEvent) {
        if (clientManager.getAuthToken() == muteUserEvent.getAuthToken()){
            User user = clientManager.getOnlineUsers().get(muteUserEvent.getAuthToken());
            try {
                boolean state = false;
                user = clientManager.getContext().userDataBase.Load(user.getId());
                User selected = clientManager.getContext().userDataBase.Load(muteUserEvent.getUserCopy().getId());
                if (!user.getMutedUsers().contains(selected.getId())) {
                    user.getMutedUsers().add(selected.getId());
                    logger.info("the user : " + user.getUserName() + " muted the user : " + selected.getUserName());
                } else {
                    user.getMutedUsers().remove(Integer.valueOf(selected.getId()));
                    logger.info("the user : " + user.getUserName() + " unMuted the user : " + selected.getUserName());
                    state = true;
                }
                clientManager.getContext().userDataBase.update(user);
            }catch (Throwable throwable){
                return new MuteUserResponse(false);
            }
        }
        return null;
    }

    public Response updateProfile(UpdateProfileDetailsEvent updateProfileDetailsEvent) {
        if (clientManager.getAuthToken() == updateProfileDetailsEvent.getAuthToken()){
            User user = null;
            User requested = clientManager.getOnlineUsers().get(updateProfileDetailsEvent.getAuthToken());
            try {
                requested = clientManager.getContext().userDataBase.Load(requested.getId());
                user = clientManager.getContext().userDataBase.Load(updateProfileDetailsEvent.getSelected().getId());
                String name = user.getFirstName() + " " +user.getLastName();
                String username = user.getUserName();
                String birthDate = "Private";
                String email = "Private";
                String phoneNumber = "Private";
                String lastSeen = "";
                boolean followingState;
                boolean onlineState = false;
                MyImage image = null;
                if (user.getPrivacyStateItems()[0]){birthDate = user.getBirthDate().toString();}
                if (user.getPrivacyStateItems()[1]){email = user.getEmailAddress();}
                if (user.getPrivacyStateItems()[2]){phoneNumber = user.getPhoneNumber();}
                switch (user.getLastSeenState()){
                    case 1:
                        lastSeen = user.getLastSeen().toString();
                        break;
                    case 2:
                        if (user.getFollowers().contains(requested.getId())){
                            lastSeen = user.getLastSeen().toString();
                        }
                        else {lastSeen = "last seen Recently";}
                        break;
                    case 3:
                        lastSeen = "last seen Recently";
                        break;
                }
                followingState = user.getFollowers().contains(requested.getId());
                if (user.getImageId() != 0){
                    image = clientManager.getContext().imageDataBase.Load(user.getImageId());
                }
                for (Integer token:clientManager.getOnlineUsers().keySet()) {
                    User test = clientManager.getOnlineUsers().get(token);
                    if (test.getId() == user.getId()){
                        onlineState = true;
                        break;
                    }
                }
                return new UpdateProfileDetailsResponse(username ,name , birthDate , email , phoneNumber , lastSeen , followingState , image , onlineState);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
