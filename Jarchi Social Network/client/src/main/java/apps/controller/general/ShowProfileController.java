package apps.controller.general;

import apps.view.pages.generalPages.ProfilePage;
import controller.Controller;
import controller.LogicalAgent;
import javafx.scene.image.Image;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.generalEvents.BlockUserEvent;
import shared.events.generalEvents.FollowUserEvent;
import shared.events.generalEvents.MuteUserEvent;
import shared.events.generalEvents.UpdateProfileDetailsEvent;
import shared.events.messagingEvents.general.SearchForMessageEvent;
import shared.responses.generalResponses.BlockUserResponse;
import shared.responses.generalResponses.FollowUserResponse;
import shared.responses.generalResponses.MuteUserResponse;
import shared.responses.generalResponses.UpdateProfileDetailsResponse;
import shared.responses.messagingResponses.general.SearchForMessageResponse;
import view.ExtraPageChangers;


public class ShowProfileController extends Controller {

    public ShowProfileController() {
    }

    public void updateProfileDetails(ProfilePage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        UpdateProfileDetailsEvent updateProfileDetailsEvent = new UpdateProfileDetailsEvent(LogicalAgent.authToken, page.getSelectedUser());
        Runnable runnable = () -> {
            synchronized (LogicalAgent.syncHandler.PROFILE_SYNC) {
                UpdateProfileDetailsResponse updateProfileDetailsResponse = (UpdateProfileDetailsResponse) socketEventSender.request(updateProfileDetailsEvent);
                page.getName().setText(updateProfileDetailsResponse.getName());
                page.getUsername().setText(updateProfileDetailsResponse.getUserName());
                page.setBirthDayLabelText(updateProfileDetailsResponse.getBirthDate());
                page.setEmailLabelText(updateProfileDetailsResponse.getEmail());
                page.setPhoneNumberLabelText(updateProfileDetailsResponse.getPhoneNumber());
                page.setLastSeenLabelText(updateProfileDetailsResponse.getLastSeen());
                page.setFollowLabelText(updateProfileDetailsResponse.isFollowingState());
                page.setFollowButtonText(updateProfileDetailsResponse.isFollowingState());
                page.setOnlineStateText(updateProfileDetailsResponse.isOnlineState());
                if (updateProfileDetailsResponse.getImage() != null) {
                    Image image = updateProfileDetailsResponse.getImage().convertToImage();
                    page.setLoadedImage(image);
                }
            }
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void followUserRequest(ProfilePage page) {
        synchronized (LogicalAgent.syncHandler.PROFILE_SYNC){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            FollowUserEvent followUserEvent = new FollowUserEvent(LogicalAgent.authToken, page.getSelectedUser());
            FollowUserResponse followUserResponse = (FollowUserResponse) socketEventSender.request(followUserEvent);
        }
    }

    public void blockUserRequest(ProfilePage page) {
        synchronized (LogicalAgent.syncHandler.PROFILE_SYNC){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            BlockUserEvent blockUserEvent = new BlockUserEvent(LogicalAgent.authToken, page.getSelectedUser());
            BlockUserResponse blockUserResponse = (BlockUserResponse) socketEventSender.request(blockUserEvent);
            page.setBlockButtonText(blockUserResponse.isState());
        }
    }

    public void muteUserRequest(ProfilePage page) {
        synchronized (LogicalAgent.syncHandler.PROFILE_SYNC){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            MuteUserEvent muteUserEvent = new MuteUserEvent(LogicalAgent.authToken, page.getSelectedUser());
            MuteUserResponse muteUserResponse = (MuteUserResponse) socketEventSender.request(muteUserEvent);
            page.setMuteButtonText(muteUserResponse.isState());
        }
    }

    public void goToSendMessagePage(ProfilePage page) {
        synchronized (LogicalAgent.syncHandler.PROFILE_SYNC){
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            SearchForMessageEvent searchForMessageEvent = new SearchForMessageEvent(LogicalAgent.authToken, page.getUsername().getText());
            SearchForMessageResponse searchForMessageResponse = (SearchForMessageResponse) socketEventSender.request(searchForMessageEvent);
            if (searchForMessageResponse.getSendingState() == 1) {
                LogicalAgent.pauseFirstThread();
                ExtraPageChangers.goToMessageHistory(searchForMessageResponse.getUsersInChat());
            }
        }
    }
}
