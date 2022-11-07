package managers;

import database.Context;
import network.SocketResponseSender;
import pageHandlers.*;
import shared.events.*;
import shared.events.explorerEvents.GetMainTweetsEvent;
import shared.events.explorerEvents.SearchAccountEvent;
import shared.events.generalEvents.*;
import shared.events.messagingEvents.general.*;
import shared.events.messagingEvents.group.*;
import shared.events.messagingEvents.multiple.*;
import shared.events.messagingEvents.pv.*;
import shared.events.personalEvents.*;
import shared.events.personalEvents.notification.*;
import shared.events.settingEvents.*;
import shared.events.timelineEvents.*;
import shared.formEvents.LoginFormEvent;
import shared.formEvents.RegistrationFormEvent;
import shared.models.User;
import shared.responses.Response;

import java.security.SecureRandom;
import java.util.HashMap;


public class ClientManager extends Thread implements EventVisitor {
    private final SocketResponseSender socketResponseSender;
    private final SecureRandom secureRandom;
    private int authToken;
    private final Context context;
    private final BotManager botManager;
    private final HashMap<Integer, User> onlineUsers;
    private final SignUpPageHandler signUpPageHandler = new SignUpPageHandler(this);
    private final PersonalPageHandler personalPageHandler = new PersonalPageHandler(this);
    private final SettingPageHandler settingPageHandler = new SettingPageHandler(this);
    private final TimeLinePageHandler timeLinePageHandler = new TimeLinePageHandler(this);
    private final MessagingPageHandler messagingPageHandler = new MessagingPageHandler(this);
    private final ExplorerPageHandler explorerPageHandler = new ExplorerPageHandler(this);
    private final GeneralPageHandler generalPageHandler = new GeneralPageHandler(this);

    public ClientManager(SocketResponseSender socketResponseSender, SecureRandom secureRandom, Context context, HashMap<Integer, User> onlineUsers , BotManager botManager) {
        this.secureRandom = secureRandom;
        this.socketResponseSender = socketResponseSender;
        this.context = context;
        this.onlineUsers = onlineUsers;
        this.botManager = botManager;
    }

    public Context getContext() {
        return context;
    }

    public SecureRandom getSecureRandom() {
        return secureRandom;
    }

    public HashMap<Integer, User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setAuthToken(int authToken) {
        this.authToken = authToken;
    }

    public int getAuthToken() {
        return authToken;
    }

    public BotManager getBotManager() {
        return botManager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                socketResponseSender.sendResponse(socketResponseSender.getEvent().visit(this));
            }
        } catch (Throwable disconnected) {
            socketResponseSender.closeResponseSender();
            disconnectUser();
        }
    }

    public void disconnectUser() {
        onlineUsers.remove(authToken);
    }


    @Override
    public Response registration(RegistrationFormEvent registrationFormEvent) {
        return signUpPageHandler.register(registrationFormEvent);
    }
    @Override
    public Response login(LoginFormEvent loginFormEvent) {
        return signUpPageHandler.login(loginFormEvent);
    }
    @Override
    public Response accountActivation(AccountActivationEvent accountActivationEvent) {
        return settingPageHandler.accountActivation(accountActivationEvent);
    }
    @Override
    public Response changePassword(ChangePasswordEvent changePasswordEvent) {
        return settingPageHandler.changePassword(changePasswordEvent);
    }
    @Override
    public Response deleteAccount(DeleteAccountEvent deleteAccountEvent) {
        return settingPageHandler.deleteAccount(deleteAccountEvent);
    }
    @Override
    public Response changeGeneralPrivacy(GeneralPrivacyEvent generalPrivacyEvent) {
        return settingPageHandler.changeGeneralPrivacy(generalPrivacyEvent);
    }
    @Override
    public Response changeItemPrivacy(ItemPrivacyEvent itemPrivacyEvent) {
        return settingPageHandler.changeItemPrivacy(itemPrivacyEvent);
    }
    @Override
    public Response lastSeen(LastSeenEvent lastSeenEvent) {
        return settingPageHandler.changeLastSeenState(lastSeenEvent);
    }
    @Override
    public Response loadBlacklist(LoadBlacklistEvent loadBlacklistEvent) {
        return personalPageHandler.loadBlackList(loadBlacklistEvent);
    }
    @Override
    public Response loadFollowers(LoadFollowersEvent loadFollowersEvent) {
        return personalPageHandler.loadFollowers(loadFollowersEvent);
    }
    @Override
    public Response loadFollowings(LoadFollowingsEvent loadFollowingsEvent) {
        return personalPageHandler.loadFollowings(loadFollowingsEvent);
    }
    @Override
    public Response changeFirstName(ChangeFirstNameEvent changeFirstNameEvent) {
        return personalPageHandler.changeFirstName(changeFirstNameEvent);
    }
    @Override
    public Response changeLastName(ChangeLastNameEvent changeLastNameEvent) {
        return personalPageHandler.changeLastName(changeLastNameEvent);
    }
    @Override
    public Response unfollow(UnfollowUserEvent unfollowUserEvent) {
        return personalPageHandler.unfollow(unfollowUserEvent);
    }
    @Override
    public Response unblock(UnblockUserEvent unblockUserEvent) {
        return personalPageHandler.unblock(unblockUserEvent);
    }
    @Override
    public Response loadMyTweets(LoadMyTweetsEvent loadMyTweetsEvent) {
        return personalPageHandler.loadMyTweets(loadMyTweetsEvent);
    }
    @Override
    public Response loadMyRequests(LoadMyRequestsEvent loadMyRequestsEvent) {
        return personalPageHandler.loadMyRequests(loadMyRequestsEvent);
    }
    @Override
    public Response loadNewRequests(LoadNewRequestsEvent loadNewRequestsEvent) {
        return personalPageHandler.loadNewRequests(loadNewRequestsEvent);
    }
    @Override
    public Response loadFollowingState(LoadFollowingStateEvent loadFollowingStateEvent) {
        return personalPageHandler.loadFollowingState(loadFollowingStateEvent);
    }
    @Override
    public Response answerRequest(AnswerRequestEvent answerRequestEvent) {
        return personalPageHandler.answerRequest(answerRequestEvent);
    }
    @Override
    public Response deleteSeenNotifications(DeleteSeenNotificationsEvent deleteSeenNotificationsEvent) {
        return personalPageHandler.deleteSeenNotifications(deleteSeenNotificationsEvent);
    }
    @Override
    public Response blockWriter(BlockWriterEvent blockWriterEvent) {
        return timeLinePageHandler.blockWriter(blockWriterEvent);
    }
    @Override
    public Response forwardTweet(ForwardTweetEvent forwardTweetEvent) {
        return timeLinePageHandler.forwardTweet(forwardTweetEvent);
    }
    @Override
    public Response likeOrDislike(LikeOrDislikeTweetEvent likeOrDislikeTweetEvent) {
        return timeLinePageHandler.likeOrDislike(likeOrDislikeTweetEvent);
    }
    @Override
    public Response loadComments(LoadCommentsEvent loadCommentsEvent) {
        return timeLinePageHandler.loadComments(loadCommentsEvent);
    }
    @Override
    public Response muteWriter(MuteWriterEvent muteWriterEvent) {
        return timeLinePageHandler.muteWriter(muteWriterEvent);
    }
    @Override
    public Response reportTweet(ReportTweetEvent reportTweetEvent) {
        return timeLinePageHandler.reportTweet(reportTweetEvent);
    }
    @Override
    public Response resendTweet(ResendTweetEvent resendTweetEvent) {
        return timeLinePageHandler.resendTweet(resendTweetEvent);
    }
    @Override
    public Response saveTweet(SaveTweetEvent saveTweetEvent) {
        return timeLinePageHandler.saveTweet(saveTweetEvent);
    }
    @Override
    public Response writeComment(WriteCommentEvent writeCommentEvent) {
        return timeLinePageHandler.writeComment(writeCommentEvent);
    }
    @Override
    public Response getFirstTweetSeries(GetFirstTweetSeriesEvent getFirstTweetSeriesEvent) {
        return timeLinePageHandler.getFirstTweetSeries(getFirstTweetSeriesEvent);
    }
    @Override
    public Response deleteMessage(DeleteMessageEvent deleteMessageEvent) {
        return messagingPageHandler.deleteMessage(deleteMessageEvent);
    }
    @Override
    public Response editMessage(EditMessageEvent editMessageEvent) {
        return messagingPageHandler.editMessage(editMessageEvent);
    }
    @Override
    public Response loadAllGroups(LoadAllGroupsEvent loadAllGroupsEvent) {
        return messagingPageHandler.loadAllGroups(loadAllGroupsEvent);
    }
    @Override
    public Response loadMyUser(LoadMyUserEvent loadMyUserEvent) {
        return messagingPageHandler.loadMyUser(loadMyUserEvent);
    }
    @Override
    public Response loadUsernamesAndUnreadMessages(LoadUsernamesAndUnreadMessagesEvent loadUsernamesAndUnreadMessagesEvent) {
        return messagingPageHandler.loadUsernamesAndUnreadMessages(loadUsernamesAndUnreadMessagesEvent);
    }
    @Override
    public Response searchForMessage(SearchForMessageEvent searchForMessageEvent) {
        return messagingPageHandler.searchForMessage(searchForMessageEvent);
    }
    @Override
    public Response addMemberToGroup(AddMemberToGroupEvent addMemberToGroupEvent) {
        return messagingPageHandler.addMemberToGroup(addMemberToGroupEvent);
    }
    @Override
    public Response loadMessage(LoadMessagesEvent loadMessagesEvent) {
        return messagingPageHandler.loadMessage(loadMessagesEvent);
    }
    @Override
    public Response loadUsers(LoadUsersEvent loadUsersEvent) {
        return messagingPageHandler.loadUsers(loadUsersEvent);
    }
    @Override
    public Response messageShown(MessageShownEvent messageShownEvent) {
        return messagingPageHandler.messageShown(messageShownEvent);
    }
    @Override
    public Response writeNewMessageInGroup(WriteNewMessageInGroupEvent writeNewMessageInGroupEvent) {
        return messagingPageHandler.writeNewMessageInGroup(writeNewMessageInGroupEvent);
    }
    @Override
    public Response addMemberToCategory(AddMemberToCategoryEvent addMemberToCategoryEvent) {
        return messagingPageHandler.addMemberToCategory(addMemberToCategoryEvent);
    }
    @Override
    public Response createNewCategoryName(CreateNewCategoryNameEvent createNewCategoryNameEvent) {
        return messagingPageHandler.createNewCategoryName(createNewCategoryNameEvent);
    }
    @Override
    public Response removeCategory(RemoveCategoryEvent removeCategoryEvent) {
        return messagingPageHandler.removeCategory(removeCategoryEvent);
    }
    @Override
    public Response removeMemberFromCategory(RemoveMemberFromCategoryEvent removeMemberFromCategoryEvent) {
        return messagingPageHandler.removeMemberFromCategory(removeMemberFromCategoryEvent);
    }
    @Override
    public Response sendMessageToGroup(SendMessageToGroupEvent sendMessageToGroupEvent) {
        return messagingPageHandler.sendMessageToGroup(sendMessageToGroupEvent);
    }
    @Override
    public Response startMultipleMessaging(StartMultipleMessagingEvent startMultipleMessagingEvent) {
        return messagingPageHandler.startMultipleMessaging(startMultipleMessagingEvent);
    }
    @Override
    public Response startSavedMessages(StartSavedMessagesEvent startSavedMessagesEvent) {
        return messagingPageHandler.startSavedMessages(startSavedMessagesEvent);
    }
    @Override
    public Response writeMessageInPV(WriteMessageInPVEvent writeMessageInPVEvent) {
        return messagingPageHandler.writeMessageInPV(writeMessageInPVEvent);
    }
    @Override
    public Response writeMessageInSavedMessages(WriteMessageInSavedMessagesEvent writeMessageInSavedMessagesEvent) {
        return messagingPageHandler.writeMessageInSavedMessages(writeMessageInSavedMessagesEvent);
    }
    @Override
    public Response createNewGroup(CreateNewGroupEvent createNewGroupEvent) {
        return messagingPageHandler.createNewGroup (createNewGroupEvent);
    }
    @Override
    public Response getMainTweets(GetMainTweetsEvent getMainTweetsEvent) {
        return explorerPageHandler.getMainTweets(getMainTweetsEvent);
    }
    @Override
    public Response loadImage(LoadImageEvent loadImageEvent) {
        return generalPageHandler.loadImage(loadImageEvent);
    }
    @Override
    public Response uploadProfileImage(UploadProfileImageEvent uploadProfileImageEvent) {
        return personalPageHandler.uploadProfileImage(uploadProfileImageEvent);
    }
    @Override
    public Response writeNewTweet(WriteNewTweetEvent writeNewTweetEvent) {
        return personalPageHandler.writeNewTweet(writeNewTweetEvent);
    }
    @Override
    public Response loadSaveMessagesPane(LoadSaveMessagePaneEvent loadSaveMessagePaneEvent) {
        return messagingPageHandler.loadSavedMessagesPane(loadSaveMessagePaneEvent);
    }
    @Override
    public Response searchAccount(SearchAccountEvent searchAccountEvent) {
        return explorerPageHandler.searchAccount(searchAccountEvent);
    }
    @Override
    public Response blockUser(BlockUserEvent blockUserEvent) {
        return generalPageHandler.blockUser(blockUserEvent);
    }
    @Override
    public Response followUser(FollowUserEvent followUserEvent) {
        return generalPageHandler.followUser(followUserEvent);
    }
    @Override
    public Response muteUser(MuteUserEvent muteUserEvent) {
        return generalPageHandler.muteUser(muteUserEvent);
    }
    @Override
    public Response updateProfile(UpdateProfileDetailsEvent updateProfileDetailsEvent) {
        return generalPageHandler.updateProfile(updateProfileDetailsEvent);
    }
    @Override
    public Response showMyInfo(ShowMyInfoEvent showMyInfoEvent) {
        return settingPageHandler.showMyInfo(showMyInfoEvent);
    }
    @Override
    public Response loadMessagesInPV(LoadMessagesInPVEvent loadMessagesInPVEvent) {
        return messagingPageHandler.loadMessagesInPV(loadMessagesInPVEvent);
    }
    @Override
    public Response logOut(LogOutEvent logOutEvent) {
        return settingPageHandler.logOut(logOutEvent);
    }
    @Override
    public Response leaveGroup(LeaveGroupEvent leaveGroupEvent) {
        return messagingPageHandler.leaveGroup(leaveGroupEvent);
    }
    @Override
    public Response writeScheduledMessageInGroup(WriteScheduledMessageInGroupEvent writeScheduledMessageInGroupEvent) {
        return messagingPageHandler.writeNewScheduledMessageInGroup(writeScheduledMessageInGroupEvent);
    }
}
