package pageHandlers;

import managers.ClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.events.messagingEvents.general.*;
import shared.events.messagingEvents.group.*;
import shared.events.messagingEvents.multiple.*;
import shared.events.messagingEvents.pv.*;
import shared.models.*;
import shared.responses.Response;
import shared.responses.messagingResponses.functionalItems.ItemMessage1;
import shared.responses.messagingResponses.functionalItems.ItemMessage2;
import shared.responses.messagingResponses.general.*;
import shared.responses.messagingResponses.group.*;
import shared.responses.messagingResponses.multiple.*;
import shared.responses.messagingResponses.pv.*;

import java.io.IOException;
import java.util.*;

public class MessagingPageHandler extends PageHandler {
    static private final Logger logger = LogManager.getLogger(MessagingPageHandler.class);

    public MessagingPageHandler(ClientManager clientManager) {
        super(clientManager);
    }


    public Response deleteMessage(DeleteMessageEvent deleteMessageEvent) {
        if (clientManager.getAuthToken() == deleteMessageEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(deleteMessageEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                Message message = clientManager.getContext().messageDataBase.Load(deleteMessageEvent.getMessageId());
                if (message.getSenderId() != user.getId()) {
                    return null;
                }
                clientManager.getContext().messageDataBase.delete(message.getId());
                logger.info("the message with id : " + message.getId() + " is deleted");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return new DeleteMessageResponse();
        }
        return null;
    }

    public Response editMessage(EditMessageEvent editMessageEvent) {
        if (clientManager.getAuthToken() == editMessageEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(editMessageEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                Message message = clientManager.getContext().messageDataBase.Load(editMessageEvent.getMessageId());
                if (message.getSenderId() != user.getId()) {
                    return null;
                }
                String newText = editMessageEvent.getNewText();
                message.setText(newText);
                logger.info("the message with id : " + message.getId() + " is edited");
                clientManager.getContext().messageDataBase.update(message);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return new EditMessageResponse();
        }
        return null;
    }

    public Response loadAllGroups(LoadAllGroupsEvent loadAllGroupsEvent) {
        if (clientManager.getAuthToken() == loadAllGroupsEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadAllGroupsEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ArrayList<Group> allGroups = new ArrayList<>();
            for (int groupId : user.getAllGroups()) {
                try {
                    Group group = clientManager.getContext().groupDataBase.Load(groupId);
                    allGroups.add(group);
                } catch (Throwable ignored) {
                }
            }
            return new LoadAllGroupsResponses(allGroups);
        }
        return null;
    }

    public Response loadMyUser(LoadMyUserEvent loadMyUserEvent) {
        if (clientManager.getAuthToken() == loadMyUserEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadMyUserEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                return new LoadMyUserResponse(new UserCopy(user));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response loadUsernamesAndUnreadMessages(LoadUsernamesAndUnreadMessagesEvent loadUsernamesAndUnreadMessagesEvent) {
        if (clientManager.getAuthToken() == loadUsernamesAndUnreadMessagesEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadUsernamesAndUnreadMessagesEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ArrayList<ItemMessage1> list = new ArrayList<>();
            for (Integer userId : user.getAllMessages().keySet()) {
                try {
                    User selected = clientManager.getContext().userDataBase.Load(userId);
                    ArrayList<Message> messages = new ArrayList<>();
                    for (Integer messageId : user.getAllMessages().get(userId)) {
                        try {
                            Message message = clientManager.getContext().messageDataBase.Load(messageId);
                            messages.add(message);
                        } catch (Throwable ignored) {
                        }
                    }
                    list.add(new ItemMessage1(new UserCopy(selected), messages));
                } catch (Throwable ignored) {
                }
            }
            return new LoadUsernamesAndUnreadMessagesResponse(list);
        }
        return null;
    }

    public Response loadMessagesInPV(LoadMessagesInPVEvent loadMessagesInPVEvent) {
        if (clientManager.getAuthToken() == loadMessagesInPVEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadMessagesInPVEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                if (!user.getAllMessages().containsKey(loadMessagesInPVEvent.getContact().getId())) {
                    user.getAllMessages().put(loadMessagesInPVEvent.getContact().getId(), new ArrayList<>());
                }
                ArrayList<Integer> messageIds = user.getAllMessages().get(loadMessagesInPVEvent.getContact().getId());
                ArrayList<MessageCopy> messages = new ArrayList<>();
                for (Integer id : messageIds) {
                    try {
                        Message message = clientManager.getContext().messageDataBase.Load(id);
                        messages.add(new MessageCopy(message));
                    } catch (Throwable ignored) {
                    }
                }
                return new LoadMessagesInPVResponse(messages);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response searchForMessage(SearchForMessageEvent searchForMessageEvent) {
        if (clientManager.getAuthToken() == searchForMessageEvent.getAuthToken()) {
            try {
                User user = clientManager.getOnlineUsers().get(searchForMessageEvent.getAuthToken());
                user = clientManager.getContext().userDataBase.Load(user.getId());
                User selected = clientManager.getContext().userDataBase.Load(clientManager.getContext().previousData.getAllUserNames().get(searchForMessageEvent.getUserName()));
                if ((user.getFollowers().contains(selected.getId()) || user.getFollowings().contains(selected.getId())) &&
                        !user.getBlackList().contains(selected.getId()) && !selected.getBlackList().contains(user.getId())) {
                    ArrayList<Message> messages = new ArrayList<>();
                    if (user.getAllMessages().containsKey(selected.getId())) {
                        messages = loadMessages(user.getAllMessages().get(selected.getId()));
                    } else {
                        user.getAllMessages().put(selected.getId(), new ArrayList<>());
                        selected.getAllMessages().put(user.getId(), new ArrayList<>());
                    }
                    ArrayList<UserCopy> usersInChat = new ArrayList<>();
                    usersInChat.add(new UserCopy(user));
                    usersInChat.add(new UserCopy(selected));
                    logger.info("the user : " + user.getUserName() + " started a chat with the user : " + selected.getUserName());
                    return new SearchForMessageResponse(1, messages, usersInChat);
                } else {
                    return new SearchForMessageResponse(2, null, null);
                }
            } catch (Throwable ignored) {
                return new SearchForMessageResponse(3, null, null);
            }
        }
        return null;
    }

    public Response addMemberToGroup(AddMemberToGroupEvent addMemberToGroupEvent) {
        if (clientManager.getAuthToken() == addMemberToGroupEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(addMemberToGroupEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ArrayList<Group> allGroups = new ArrayList<>();
            for (Integer groupId : user.getAllGroups()) {
                try {
                    Group group = clientManager.getContext().groupDataBase.Load(groupId);
                    allGroups.add(group);
                } catch (Throwable ignored) {
                }
            }
            String groupName = addMemberToGroupEvent.getGroupName();
            if (checkIfTheGroupExists(allGroups, groupName)) {
                String username = addMemberToGroupEvent.getUserName();
                Group group = getGroupFromList(allGroups, groupName);
                try {
                    int userid = clientManager.getContext().previousData.getAllUserNames().get(username);
                    User selected = clientManager.getContext().userDataBase.Load(userid);
                    if ((user.getFollowers().contains(selected.getId()) || user.getFollowings().contains(selected.getId())) &&
                            !user.getBlackList().contains(selected.getId()) && !selected.getBlackList().contains(user.getId())) {
                        assert group != null;
                        if (!group.getUserIds().contains(selected.getId())) {
                            group.getUserIds().add(selected.getId());
                            selected.getAllGroups().add(group.getId());
                            logger.info("the user : " + user.getUserName() + " added the user : " + selected.getUserName() + " to a group");
                            clientManager.getContext().groupDataBase.update(group);
                            clientManager.getContext().userDataBase.update(selected);
                        }
                        return new AddMemberToGroupResponse(1);
                    } else {
                        return new AddMemberToGroupResponse(2);
                    }
                } catch (Throwable throwable) {
                    return new AddMemberToGroupResponse(2);
                }
            } else {
                return new AddMemberToGroupResponse(3);
            }
        }
        return null;
    }

    public Response loadMessage(LoadMessagesEvent loadMessagesEvent) {
        if (clientManager.getAuthToken() == loadMessagesEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadMessagesEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                ArrayList<MessageCopy> allMessages = new ArrayList<>();
                Group group = clientManager.getContext().groupDataBase.Load(loadMessagesEvent.getGroupId());
                if (!group.getUserIds().contains(user.getId())) return null;
                for (int messageId : group.getAllMessages()) {
                    try {
                        Message message = clientManager.getContext().messageDataBase.Load(messageId);
                        allMessages.add(new MessageCopy(message));
                    } catch (Throwable ignored) {
                    }
                }
                return new LoadMessagesResponse(allMessages);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response loadUsers(LoadUsersEvent loadUsersEvent) {
        if (clientManager.getAuthToken() == loadUsersEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadUsersEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                Group group = clientManager.getContext().groupDataBase.Load(loadUsersEvent.getGroupId());
                if (!group.getUserIds().contains(user.getId())) return null;
                ArrayList<UserCopy> allUsers = new ArrayList<>();
                for (int userId : group.getUserIds()) {
                    try {
                        User user1 = clientManager.getContext().userDataBase.Load(userId);
                        allUsers.add(new UserCopy(user1));
                    } catch (Throwable ignored) {
                    }
                }
                return new LoadUsersResponse(allUsers);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response messageShown(MessageShownEvent messageShownEvent) {
        if (clientManager.getAuthToken() == messageShownEvent.getAuthToken()) {
            int id = messageShownEvent.getMessageId();
            try {
                Message message = clientManager.getContext().messageDataBase.Load(id);
                message.setIsSeen(4);
                clientManager.getContext().messageDataBase.update(message);
            } catch (Throwable ignored) {
            }
        }
        return null;
    }

    public Response writeNewMessageInGroup(WriteNewMessageInGroupEvent writeNewMessageInGroupEvent) {
        if (clientManager.getAuthToken() == writeNewMessageInGroupEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(writeNewMessageInGroupEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String text = writeNewMessageInGroupEvent.getMessageText();
                Group group = clientManager.getContext().groupDataBase.Load(writeNewMessageInGroupEvent.getGroupId());
                if (text.startsWith("/")) {
                    boolean botFound = false;
                    int botId = 0;
                    for (Integer id : group.getUserIds()) {
                        if (clientManager.getBotManager().getAvailableBots().containsKey(id)) {
                            botFound = true;
                            botId = id;
                            break;
                        }
                    }
                    if (botFound) {
                        clientManager.getBotManager().handleRequest(text, botId, user.getId(), group.getId());
                    }
                    return new WriteNewMessageInGroupResponse();
                }
                Message message = new Message(user.getId(), text, false, clientManager.getContext().messageDataBase.getLastId());
                if (writeNewMessageInGroupEvent.getImage() != null) {
                    MyImage myImage = new MyImage(writeNewMessageInGroupEvent.getImage(), clientManager.getContext().imageDataBase.getLastId() + 1);
                    message.setImageId(myImage.getId());
                    logger.info("the user : " + user.getUserName() + " uploaded profile image with id : : " + myImage.getId());
                    clientManager.getContext().imageDataBase.update(myImage);
                }
                if (group.getUserIds().contains(user.getId())) {
                    group.getAllMessages().add(message.getId());
                }
                clientManager.getContext().groupDataBase.update(group);
                clientManager.getContext().messageDataBase.update(message);
                return new WriteNewMessageInGroupResponse();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response writeNewScheduledMessageInGroup(WriteScheduledMessageInGroupEvent writeScheduledMessageInGroupEvent) {
        if (clientManager.getAuthToken() == writeScheduledMessageInGroupEvent.getAuthToken()) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        User user = clientManager.getOnlineUsers().get(writeScheduledMessageInGroupEvent.getAuthToken());
                        user = clientManager.getContext().userDataBase.Load(user.getId());
                        String text = writeScheduledMessageInGroupEvent.getMessageText();
                        Group group = clientManager.getContext().groupDataBase.Load(writeScheduledMessageInGroupEvent.getGroupId());
                        if (text.startsWith("/")) {
                            boolean botFound = false;
                            int botId = 0;
                            for (Integer id : group.getUserIds()) {
                                if (clientManager.getBotManager().getAvailableBots().containsKey(id)) {
                                    botFound = true;
                                    botId = id;
                                    break;
                                }
                            }
                            if (botFound) {
                                clientManager.getBotManager().handleRequest(text, botId, user.getId(), group.getId());
                            }
                        }
                        Message message = new Message(user.getId(), text, false, clientManager.getContext().messageDataBase.getLastId());
                        if (writeScheduledMessageInGroupEvent.getImage() != null) {
                            MyImage myImage = new MyImage(writeScheduledMessageInGroupEvent.getImage(), clientManager.getContext().imageDataBase.getLastId() + 1);
                            message.setImageId(myImage.getId());
                            logger.info("the user : " + user.getUserName() + " uploaded profile image with id : : " + myImage.getId());
                            clientManager.getContext().imageDataBase.update(myImage);
                        }
                        if (group.getUserIds().contains(user.getId())) {
                            group.getAllMessages().add(message.getId());
                        }
                        clientManager.getContext().groupDataBase.update(group);
                        clientManager.getContext().messageDataBase.update(message);
                    }catch (Throwable throwable){
                        throwable.printStackTrace();
                    }
                }
            };
            try {
                Timer timer = new Timer();
                int seconds = Integer.parseInt(writeScheduledMessageInGroupEvent.getSecond());
                int minutes = Integer.parseInt(writeScheduledMessageInGroupEvent.getMinute());
                int hours = Integer.parseInt(writeScheduledMessageInGroupEvent.getHour());
                long total = seconds + (60 * minutes) + (3600 * hours);
                total = total *1000;
                timer.schedule(timerTask, total);
            }catch (Throwable ignored){}
            return new WriteScheduledMessageInGroupResponse();
        }
        return null;
    }

    public Response addMemberToCategory(AddMemberToCategoryEvent addMemberToCategoryEvent) {
        if (clientManager.getAuthToken() == addMemberToCategoryEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(addMemberToCategoryEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            String selectedUsername = addMemberToCategoryEvent.getMemberName();
            String categoryName = addMemberToCategoryEvent.getCategoryName();
            if (addMemberToCategoryEvent.isOldCategory() && !user.getAllCategories().containsKey(categoryName)) {
                return new AddMemberToCategoryResponse(false);
            }
            try {
                int userid = clientManager.getContext().previousData.getAllUserNames().get(selectedUsername);
                User selected = clientManager.getContext().userDataBase.Load(userid);
                if ((user.getFollowers().contains(selected.getId()) || user.getFollowings().contains(selected.getId())) &&
                        !user.getBlackList().contains(selected.getId()) && !selected.getBlackList().contains(user.getId())) {
                    if (!user.getAllCategories().get(categoryName).contains(selected.getId())) {
                        user.getAllCategories().get(categoryName).add(selected.getId());
                        logger.info("the user : " + user.getUserName() + " added the user : " + selected.getUserName() + " to a category");
                        clientManager.getContext().userDataBase.update(user);
                    }
                    return new AddMemberToCategoryResponse(true);
                } else {
                    return new AddMemberToCategoryResponse(false);
                }
            } catch (Throwable throwable) {
                return new AddMemberToCategoryResponse(false);
            }
        }
        return null;
    }

    public Response createNewCategoryName(CreateNewCategoryNameEvent createNewCategoryNameEvent) {
        if (clientManager.getAuthToken() == createNewCategoryNameEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(createNewCategoryNameEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String categoryName = createNewCategoryNameEvent.getCategoryName();
                if (!user.getAllCategories().containsKey(categoryName)) {
                    user.getAllCategories().put(categoryName, new ArrayList<>());
                    logger.info("the user : " + user.getUserName() + " made a new category with name : " + categoryName);
                    clientManager.getContext().userDataBase.update(user);
                    return new CreateNewCategoryNameResponse(true);
                } else {
                    return new CreateNewCategoryNameResponse(false);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response removeCategory(RemoveCategoryEvent removeCategoryEvent) {
        if (clientManager.getAuthToken() == removeCategoryEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(removeCategoryEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String categoryName = removeCategoryEvent.getCategoryName();
                user.getAllCategories().remove(categoryName);
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return new RemoveCategoryResponse();
        }
        return null;
    }

    public Response removeMemberFromCategory(RemoveMemberFromCategoryEvent removeMemberFromCategoryEvent) {
        if (clientManager.getAuthToken() == removeMemberFromCategoryEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(removeMemberFromCategoryEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String categoryName = removeMemberFromCategoryEvent.getCategoryName();
                User member = clientManager.getContext().userDataBase.Load(removeMemberFromCategoryEvent.getMember().getId());
                user.getAllCategories().get(categoryName).remove(Integer.valueOf(member.getId()));
                logger.info("the user : " + user.getUserName() + " removed the user : " + member.getUserName() + " from category");
                clientManager.getContext().userDataBase.update(user);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return new RemoveMemberFromCategoryResponse();
        }
        return null;
    }

    public Response sendMessageToGroup(SendMessageToGroupEvent sendMessageToGroupEvent) {
        if (clientManager.getAuthToken() == sendMessageToGroupEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(sendMessageToGroupEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String text = sendMessageToGroupEvent.getText();
                User sender = clientManager.getContext().userDataBase.Load(sendMessageToGroupEvent.getSender().getId());
                ArrayList<UserCopy> receivers = sendMessageToGroupEvent.getReceivers();
                if (sender.getId() != user.getId()) {
                    return null;
                }
                Message message = new Message(sender.getId(), text, false, clientManager.getContext().messageDataBase.getLastId());
                if (sendMessageToGroupEvent.getImage() != null) {
                    MyImage myImage = new MyImage(sendMessageToGroupEvent.getImage(), clientManager.getContext().imageDataBase.getLastId() + 1);
                    message.setImageId(myImage.getId());
                    clientManager.getContext().imageDataBase.update(myImage);
                }
                ArrayList<User> usersToBeSent = loadUsers(receivers);
                for (User receiver : usersToBeSent) {
                    if (sender.getId() != receiver.getId()) {
                        if (!sender.getAllMessages().containsKey(receiver.getId())) {
                            sender.getAllMessages().put(receiver.getId(), new ArrayList<>());
                            receiver.getAllMessages().put(sender.getId(), new ArrayList<>());
                        }
                        sender.getAllMessages().get(receiver.getId()).add(message.getId());
                        receiver.getAllMessages().get(sender.getId()).add(message.getId());
                        logger.info("the user : " + sender.getUserName() + " sent a message to the user : " + receiver.getUserName());
                        clientManager.getContext().userDataBase.update(receiver);
                    }
                }
                clientManager.getContext().userDataBase.update(sender);
                clientManager.getContext().messageDataBase.update(message);
                return new SendMessageToGroupResponse();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response startMultipleMessaging(StartMultipleMessagingEvent startMultipleMessagingEvent) {
        if (clientManager.getAuthToken() == startMultipleMessagingEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(startMultipleMessagingEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            HashMap<String, ArrayList<Integer>> list = user.getAllCategories();
            ArrayList<ItemMessage2> items = new ArrayList<>();
            for (String name : list.keySet()) {
                ArrayList<UserCopy> users = new ArrayList<>();
                for (int userid : list.get(name)) {
                    try {
                        User user1 = clientManager.getContext().userDataBase.Load(userid);
                        users.add(new UserCopy(user1));
                    } catch (Throwable ignored) {
                    }
                }
                items.add(new ItemMessage2(name, users));
            }
            return new StartMultipleMessagingResponse(items);
        }
        return null;
    }

    public Response startSavedMessages(StartSavedMessagesEvent startSavedMessagesEvent) {
        if (clientManager.getAuthToken() == startSavedMessagesEvent.getAuthToken()) {
            ArrayList<Tweet> savedMessages = new ArrayList<>();
            User user = clientManager.getOnlineUsers().get(startSavedMessagesEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            for (Integer tweetId : user.getSavedTweets()) {
                try {
                    Tweet tweet = clientManager.getContext().tweetDataBase.Load(tweetId);
                    savedMessages.add(tweet);
                } catch (Throwable ignored) {
                }
            }
            return new StartSavedMessagesResponse(savedMessages);
        }
        return null;
    }

    public Response writeMessageInPV(WriteMessageInPVEvent writeMessageInPVEvent) {
        if (clientManager.getAuthToken() == writeMessageInPVEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(writeMessageInPVEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                User sender = clientManager.getContext().userDataBase.Load(writeMessageInPVEvent.getSender().getId());
                User receiver = clientManager.getContext().userDataBase.Load(writeMessageInPVEvent.getReceiver().getId());
                if (user.getId() != sender.getId()) {
                    return null;
                }
                if (clientManager.getBotManager().getAvailableBots().containsKey(receiver.getId())) {
                    clientManager.getBotManager().handleRequest(writeMessageInPVEvent.getMessageText(), receiver.getId(), sender.getId(), 0);
                    return new WriteMessageInPVResponse();
                }
                Message message = new Message(sender.getId(), writeMessageInPVEvent.getMessageText(), false, clientManager.getContext().messageDataBase.getLastId());
                message.setIsSeen(2);
                if (writeMessageInPVEvent.getImage() != null) {
                    MyImage myImage = new MyImage(writeMessageInPVEvent.getImage(), clientManager.getContext().imageDataBase.getLastId() + 1);
                    message.setImageId(myImage.getId());
                    clientManager.getContext().imageDataBase.update(myImage);
                }
                for (Integer token : clientManager.getOnlineUsers().keySet()) {
                    User test = clientManager.getOnlineUsers().get(token);
                    if (test.getId() == receiver.getId()) {
                        message.setIsSeen(3);
                        break;
                    }
                }
                if (!sender.getAllMessages().containsKey(receiver.getId())) {
                    sender.getAllMessages().put(receiver.getId(), new ArrayList<>());
                }
                if (!receiver.getAllMessages().containsKey(sender.getId())) {
                    receiver.getAllMessages().put(sender.getId(), new ArrayList<>());
                }
                sender.getAllMessages().get(receiver.getId()).add(message.getId());
                receiver.getAllMessages().get(sender.getId()).add(message.getId());
                logger.info("the user : " + sender.getUserName() + " sent a message to the user : " + receiver.getUserName());
                clientManager.getContext().userDataBase.update(receiver);
                clientManager.getContext().userDataBase.update(sender);
                clientManager.getContext().messageDataBase.update(message);
                return new WriteMessageInPVResponse();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    public Response createNewGroup(CreateNewGroupEvent createNewGroupEvent) {
        if (clientManager.getAuthToken() == createNewGroupEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(createNewGroupEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ArrayList<Group> allGroups = new ArrayList<>();
            for (Integer groupId : user.getAllGroups()) {
                try {
                    Group group = clientManager.getContext().groupDataBase.Load(groupId);
                    allGroups.add(group);
                } catch (Throwable ignored) {
                }
            }
            if (!checkIfTheGroupExists(allGroups, createNewGroupEvent.getGroupName())) {
                try {
                    Group group = new Group(createNewGroupEvent.getGroupName(), clientManager.getContext().groupDataBase.getLastId());
                    group.getUserIds().add(user.getId());
                    user.getAllGroups().add(group.getId());
                    logger.info("the user : " + user.getUserName() + " made a new group.");
                    clientManager.getContext().groupDataBase.update(group);
                    clientManager.getContext().userDataBase.update(user);
                    return new CreateNewGroupResponse(group);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return null;
    }

    public Response writeMessageInSavedMessages(WriteMessageInSavedMessagesEvent writeMessageInSavedMessagesEvent) {
        if (clientManager.getAuthToken() == writeMessageInSavedMessagesEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(writeMessageInSavedMessagesEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                String text = writeMessageInSavedMessagesEvent.getText();
                if (!text.equals("")) {
                    int lastId = clientManager.getContext().commentDataBase.getLastId();
                    Tweet comment = new Tweet(text, user.getId(), lastId, false);
                    user.getSavedTweets().add(comment.getId());
                    clientManager.getContext().commentDataBase.update(comment);
                    clientManager.getContext().userDataBase.update(user);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return new WriteMessageInSavedMessagesResponse();
        }
        return null;
    }

    public Response loadSavedMessagesPane(LoadSaveMessagePaneEvent loadSaveMessagePaneEvent) {
        if (clientManager.getAuthToken() == loadSaveMessagePaneEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(loadSaveMessagePaneEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                Tweet tweet = clientManager.getContext().tweetDataBase.Load(loadSaveMessagePaneEvent.getTweetId());
                User writer = clientManager.getContext().userDataBase.Load(tweet.getUserId());
                if (tweet.getImageId() != 0) {
                    MyImage myImage = clientManager.getContext().imageDataBase.Load(tweet.getImageId());
                    return new LoadSaveMessagePaneResponse(new UserCopy(writer), myImage);
                } else {
                    return new LoadSaveMessagePaneResponse(new UserCopy(writer), null);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Response leaveGroup(LeaveGroupEvent leaveGroupEvent) {
        if (clientManager.getAuthToken() == leaveGroupEvent.getAuthToken()) {
            User user = clientManager.getOnlineUsers().get(leaveGroupEvent.getAuthToken());
            try {
                user = clientManager.getContext().userDataBase.Load(user.getId());
                Group group = clientManager.getContext().groupDataBase.Load(leaveGroupEvent.getGroupId());
                group.getUserIds().remove(Integer.valueOf(user.getId()));
                user.getAllGroups().remove(Integer.valueOf(group.getId()));
                if (group.getUserIds().size() == 0) {
                    clientManager.getContext().groupDataBase.delete(group.getId());
                }
                clientManager.getContext().groupDataBase.update(group);
                clientManager.getContext().userDataBase.update(user);
                return new LeaveGroupResponse();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private ArrayList<Message> loadMessages(ArrayList<Integer> allMessageId) {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Integer messageId : allMessageId) {
            try {
                Message message = clientManager.getContext().messageDataBase.Load(messageId);
                allMessages.add(message);
            } catch (Throwable ignored) {
            }
        }
        return allMessages;
    }

    private boolean checkIfTheGroupExists(ArrayList<Group> allGroups, String groupName) {
        for (Group group1 : allGroups) {
            if (group1.getName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    private Group getGroupFromList(ArrayList<Group> allGroups, String groupName) {
        for (Group group : allGroups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }

    private ArrayList<User> loadUsers(ArrayList<UserCopy> users) {
        ArrayList<User> list = new ArrayList<>();
        for (UserCopy userCopy : users) {
            try {
                User user = clientManager.getContext().userDataBase.Load(userCopy.getId());
                list.add(user);
            } catch (Throwable ignored) {
            }
        }
        return list;
    }
}
