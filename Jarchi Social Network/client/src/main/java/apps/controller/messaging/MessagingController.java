package apps.controller.messaging;

import apps.view.pages.messaging.generalMessagePages.CreateNewMessage;
import apps.view.pages.messaging.generalMessagePages.EditMessagePage;
import apps.view.pages.messaging.generalMessagePages.MessagePane;
import apps.view.pages.messaging.messageWithKnown.UserNamesAndUnreadMessages;
import apps.view.pages.messaging.messageWithKnown.UserNamesAndUnreadMessagesPane;
import apps.view.pages.messaging.messageWithUnknown.UserNameCheck;
import apps.view.pages.messaging.savedMessages.WriteMessageInSavedMessages;
import config.Config;
import controller.Controller;
import controller.LogicalAgent;
import javafx.scene.image.Image;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.generalEvents.LoadImageEvent;
import shared.events.messagingEvents.general.DeleteMessageEvent;
import shared.events.messagingEvents.general.EditMessageEvent;
import shared.events.messagingEvents.general.LoadUsernamesAndUnreadMessagesEvent;
import shared.events.messagingEvents.general.SearchForMessageEvent;
import shared.events.messagingEvents.group.MessageShownEvent;
import shared.events.messagingEvents.pv.WriteMessageInPVEvent;
import shared.events.messagingEvents.pv.WriteMessageInSavedMessagesEvent;
import shared.models.*;
import shared.responses.generalResponses.LoadImageResponse;
import shared.responses.messagingResponses.general.DeleteMessageResponse;
import shared.responses.messagingResponses.general.EditMessageResponse;
import shared.responses.messagingResponses.general.LoadUsernamesAndUnreadMessagesResponse;
import shared.responses.messagingResponses.general.SearchForMessageResponse;
import shared.responses.messagingResponses.pv.WriteMessageInPVResponse;
import shared.responses.messagingResponses.pv.WriteMessageInSavedMessagesResponse;
import view.ExtraPageChangers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class MessagingController extends Controller {

    public MessagingController() {
    }

    public void searchForMessaging(UserNameCheck page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        SearchForMessageEvent searchForMessageEvent = new SearchForMessageEvent(LogicalAgent.authToken, page.getUsername().getText());
        SearchForMessageResponse searchForMessageResponse = (SearchForMessageResponse) socketEventSender.request(searchForMessageEvent);
        Config config = Config.getConfig("errorMessages");
        switch (searchForMessageResponse.getSendingState()) {
            case 1:
                ExtraPageChangers.goToMessageHistory(searchForMessageResponse.getUsersInChat());
                break;
            case 2:
                page.getUserNameLabel().setText(config.getProperty(String.class, "sorry"));
                break;
            case 3:
                page.getUserNameLabel().setText(config.getProperty(String.class, "accountNotExist"));
                break;
        }
    }

    public void showMessageListForKnownUsers(UserNamesAndUnreadMessagesPane pane) {
        UserCopy user = LogicalAgent.viewManager.getUser();
        UserCopy selected = pane.getSelected();
        ArrayList<Message> messages = pane.getMessages();
        ArrayList<UserCopy> usersInChat = new ArrayList<>();
        usersInChat.add(user);
        usersInChat.add(selected);
        LogicalAgent.pauseFirstThread();
        ExtraPageChangers.goToMessageHistory(usersInChat);
    }

    public void countUnreadMessages(UserNamesAndUnreadMessagesPane pane) {
        int unreadMessages = 0;
        for (Message message : pane.getMessages()) {
            if (message.getIsSeen() != 4 && message.getSenderId() != LogicalAgent.viewManager.getUser().getId()) {
                unreadMessages++;
            }
        }
        pane.setUnreadMessages(unreadMessages);
    }

    public void startUserNamesAndUnreadMessagesPage(UserNamesAndUnreadMessages page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadUsernamesAndUnreadMessagesEvent loadUsernamesAndUnreadMessagesEvent = new LoadUsernamesAndUnreadMessagesEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            LoadUsernamesAndUnreadMessagesResponse loadUsernamesAndUnreadMessagesResponse = (LoadUsernamesAndUnreadMessagesResponse) socketEventSender.request(loadUsernamesAndUnreadMessagesEvent);
            HashMap<UserCopy, ArrayList<Message>> allMessages = loadUsernamesAndUnreadMessagesResponse.convertToMap();
            page.setAllMessages(allMessages);
            page.showTheList();
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable, 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void editMessage(EditMessagePage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        EditMessageEvent editMessageEvent = new EditMessageEvent(page.getTextArea().getText(), LogicalAgent.authToken, page.getMessage().getId());
        EditMessageResponse editMessageResponse = (EditMessageResponse) socketEventSender.request(editMessageEvent);
    }

    public void deleteMessage(MessagePane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        DeleteMessageEvent deleteMessageEvent = new DeleteMessageEvent(LogicalAgent.authToken, pane.getMessage().getId());
        DeleteMessageResponse deleteMessageResponse = (DeleteMessageResponse) socketEventSender.request(deleteMessageEvent);
    }

    public void messageShown(MessagePane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        MessageShownEvent messageShownEvent = new MessageShownEvent(LogicalAgent.authToken, pane.getMessage().getId());
        socketEventSender.request(messageShownEvent);
    }

    public void writeNewMessageInPv(CreateNewMessage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        String text = page.getMessageTextArea().getText();
        UserCopy sender = page.getUsersInChat().get(0);
        UserCopy receiver = page.getUsersInChat().get(1);
        WriteMessageInPVEvent writeMessageInPVEvent;
        if (page.getImageFile() != null) {
            File imageFile = page.getImageFile();
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(imageFile.toPath());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            writeMessageInPVEvent = new WriteMessageInPVEvent(LogicalAgent.authToken, text, sender, receiver, bytes);
        } else {
            writeMessageInPVEvent = new WriteMessageInPVEvent(LogicalAgent.authToken, text, sender, receiver, null);
        }
        WriteMessageInPVResponse writeMessageInPVResponse = (WriteMessageInPVResponse) socketEventSender.request(writeMessageInPVEvent);
    }

    public void writeMessageInSavedMessages(WriteMessageInSavedMessages page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        WriteMessageInSavedMessagesEvent writeMessageInSavedMessagesEvent = new WriteMessageInSavedMessagesEvent(LogicalAgent.authToken, page.getCommentText().getText());
        WriteMessageInSavedMessagesResponse writeMessageInSavedMessagesResponse = (WriteMessageInSavedMessagesResponse) socketEventSender.request(writeMessageInSavedMessagesEvent);
    }

    public void loadMessagePaneImage(MessagePane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        MessageCopy message = pane.getMessage();
        if (message.getImageId() != 0) {
            LoadImageEvent loadImageEvent = new LoadImageEvent(LogicalAgent.authToken, message.getImageId());
            LoadImageResponse loadImageResponse = (LoadImageResponse) socketEventSender.request(loadImageEvent);
            if (loadImageResponse.getImage() != null) {
                Image image = loadImageResponse.getImage().convertToImage();
                pane.setLoadedImage(image);
            }
        }
    }
}
