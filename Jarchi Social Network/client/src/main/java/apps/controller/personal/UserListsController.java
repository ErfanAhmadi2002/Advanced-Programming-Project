package apps.controller.personal;

import apps.view.pages.personal.blackList.BlackList;
import apps.view.pages.personal.blackList.BlockedUserPane;
import apps.view.pages.personal.followers.Followers;
import apps.view.pages.personal.followings.FollowingPane;
import apps.view.pages.personal.followings.Followings;
import controller.Controller;
import controller.LogicalAgent;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.personalEvents.*;
import shared.responses.personalResponses.*;

public class UserListsController extends Controller {

    public UserListsController() {
    }

    public void startFollowerPage (Followers page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadFollowersEvent loadFollowersEvent = new LoadFollowersEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            LoadFollowersResponse loadFollowersResponse = (LoadFollowersResponse) socketEventSender.request(loadFollowersEvent);
            page.showFollowers(loadFollowersResponse.getFollowers());
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void startFollowingsPage (Followings page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadFollowingsEvent loadFollowingsEvent = new LoadFollowingsEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            synchronized (LogicalAgent.syncHandler.PROFILE_SYNC) {
                LoadFollowingsResponse loadFollowingsResponse = (LoadFollowingsResponse) socketEventSender.request(loadFollowingsEvent);
                page.showFollowings(loadFollowingsResponse.getFollowings());
            }
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void startBlackListPage (BlackList page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadBlacklistEvent loadBlacklistEvent = new LoadBlacklistEvent(LogicalAgent.authToken);
        LoadBlacklistResponse loadBlacklistResponse = (LoadBlacklistResponse) socketEventSender.request(loadBlacklistEvent);
        page.setBlackList(loadBlacklistResponse.getBlackList());
    }

    public void unfollowUser (FollowingPane page) {
        synchronized (LogicalAgent.syncHandler.PROFILE_SYNC) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            UnfollowUserEvent unfollowUserEvent = new UnfollowUserEvent(LogicalAgent.authToken, page.getUser());
            UnfollowUserResponse unfollowUserResponse = (UnfollowUserResponse) socketEventSender.request(unfollowUserEvent);
        }
    }

    public void unblockUser (BlockedUserPane page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        UnblockUserEvent unblockUserEvent = new UnblockUserEvent(LogicalAgent.authToken , page.getUser());
        UnblockUserResponse unblockUserResponse = (UnblockUserResponse) socketEventSender.request(unblockUserEvent);
    }
}
