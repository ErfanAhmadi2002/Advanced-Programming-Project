package apps.controller.messaging;

import apps.view.pages.messaging.groupMessaging.*;
import config.Config;
import controller.Controller;
import controller.LogicalAgent;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.messagingEvents.general.LoadAllGroupsEvent;
import shared.events.messagingEvents.group.*;
import shared.formEvents.ScheduledMessageFormEvent;
import shared.models.*;
import shared.responses.messagingResponses.general.LoadMessagesResponse;
import shared.responses.messagingResponses.group.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class GroupMessagingController extends Controller {

    public GroupMessagingController() {
    }

    public void startGroupMessagingPage(GroupMessaging page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadAllGroupsEvent loadAllGroupsEvent = new LoadAllGroupsEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            synchronized (LogicalAgent.syncHandler.GROUP_SYNC) {
                LoadAllGroupsResponses loadAllGroupsResponses = (LoadAllGroupsResponses) socketEventSender.request(loadAllGroupsEvent);
                page.setAllGroups(loadAllGroupsResponses.getAllGroups());
                page.showGroups(loadAllGroupsResponses.getAllGroups());
            }
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable, 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void addMemberToGroup(AddGroupMemberPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        AddMemberToGroupEvent addMemberToGroupEvent = new AddMemberToGroupEvent(LogicalAgent.authToken, false, page.getGroup().getName(), page.getUsername().getText());
        AddMemberToGroupResponse addMemberToGroupResponse = (AddMemberToGroupResponse) socketEventSender.request(addMemberToGroupEvent);
        Config config = Config.getConfig("errorMessages");
        switch (addMemberToGroupResponse.getState()) {
            case 1:
                page.getUserNameLabel().setText("");
                break;
            case 2:
                page.getUserNameLabel().setText(config.getProperty(String.class, "sorry"));
                break;
        }
    }

    public void addMemberToNewGroup(CreateGroupPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        AddMemberToGroupEvent addMemberToGroupEvent = new AddMemberToGroupEvent(LogicalAgent.authToken, true, page.getGroupName().getText(), page.getUsername().getText());
        AddMemberToGroupResponse addMemberToGroupResponse = (AddMemberToGroupResponse) socketEventSender.request(addMemberToGroupEvent);
        Config config = Config.getConfig("errorMessages");
        switch (addMemberToGroupResponse.getState()) {
            case 1:
                page.getUserNameLabel().setText("");
                break;
            case 2:
                page.getUserNameLabel().setText(config.getProperty(String.class, "sorry"));
                break;
            case 3:
                page.getGroupNameLabel().setText(config.getProperty(String.class, "groupNotExist"));
                break;
        }
    }

    public void makeNewGroup(CreateGroupPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        CreateNewGroupEvent createNewGroupEvent = new CreateNewGroupEvent(LogicalAgent.authToken, page.getGroupName().getText());
        CreateNewGroupResponse createNewGroupResponse = (CreateNewGroupResponse) socketEventSender.request(createNewGroupEvent);
        page.getAllGroups().add(createNewGroupResponse.getGroup());
    }

    public void writeNewMessageInGroup(CreateNewGroupMessagePage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        String text = page.getMessageTextArea().getText();
        WriteNewMessageInGroupEvent writeNewMessageInGroupEvent;
        if (page.getImageFile() != null) {
            File imageFile = page.getImageFile();
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(imageFile.toPath());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            writeNewMessageInGroupEvent = new WriteNewMessageInGroupEvent(text, LogicalAgent.authToken, bytes, page.getGroup().getId());
        } else {
            writeNewMessageInGroupEvent = new WriteNewMessageInGroupEvent(text, LogicalAgent.authToken, null, page.getGroup().getId());
        }
        WriteNewMessageInGroupResponse writeNewMessageInGroupResponse = (WriteNewMessageInGroupResponse) socketEventSender.request(writeNewMessageInGroupEvent);
    }

    public void writeScheduledMessageInGroup(ScheduledMessageFormEvent scheduledMessageFormEvent) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        String text = scheduledMessageFormEvent.getMessageText();
        WriteScheduledMessageInGroupEvent writeScheduledMessageInGroupEvent;
        if (scheduledMessageFormEvent.getImageFile() != null) {
            File imageFile = scheduledMessageFormEvent.getImageFile();
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(imageFile.toPath());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            writeScheduledMessageInGroupEvent = new WriteScheduledMessageInGroupEvent(LogicalAgent.authToken, text, scheduledMessageFormEvent.getSecond(), scheduledMessageFormEvent.getMinute(), scheduledMessageFormEvent.getHour(), scheduledMessageFormEvent.getGroupId(),bytes);
        } else {
            writeScheduledMessageInGroupEvent = new WriteScheduledMessageInGroupEvent(LogicalAgent.authToken, text, scheduledMessageFormEvent.getSecond(), scheduledMessageFormEvent.getMinute(), scheduledMessageFormEvent.getHour(), scheduledMessageFormEvent.getGroupId(),null);
        }
        WriteScheduledMessageInGroupResponse writeScheduledMessageInGroupResponse = (WriteScheduledMessageInGroupResponse) socketEventSender.request(writeScheduledMessageInGroupEvent);
    }

    public void leaveGroup(GroupPane groupPane) {
        synchronized (LogicalAgent.syncHandler.GROUP_SYNC) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            LeaveGroupEvent leaveGroupEvent = new LeaveGroupEvent(LogicalAgent.authToken, groupPane.getGroup().getId());
            LeaveGroupResponse leaveGroupResponse = (LeaveGroupResponse) socketEventSender.request(leaveGroupEvent);
        }
    }

    public ArrayList<MessageCopy> getAllMessagesOfGroup(Group group) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadMessagesEvent loadMessagesEvent = new LoadMessagesEvent(LogicalAgent.authToken, group.getId());
        LoadMessagesResponse loadMessagesResponse = (LoadMessagesResponse) socketEventSender.request(loadMessagesEvent);
        return loadMessagesResponse.getAllMessages();
    }

    public ArrayList<UserCopy> getAllUsersOfGroup(Group group) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadUsersEvent loadUsersEvent = new LoadUsersEvent(LogicalAgent.authToken, group.getId());
        LoadUsersResponse loadUsersResponse = (LoadUsersResponse) socketEventSender.request(loadUsersEvent);
        return loadUsersResponse.getAllUsers();
    }

}
