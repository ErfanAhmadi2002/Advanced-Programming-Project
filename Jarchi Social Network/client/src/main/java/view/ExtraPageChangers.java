package view;

import apps.controller.messaging.GroupMessagingController;
import apps.view.pages.generalPages.ProfilePage;
import apps.view.pages.mainEntrancePages.TimeLineList;
import apps.view.pages.messaging.generalMessagePages.CreateNewMessage;
import apps.view.pages.messaging.generalMessagePages.EditMessagePage;
import apps.view.pages.messaging.generalMessagePages.MessageHistory;
import apps.view.pages.messaging.groupMessaging.AddGroupMemberPage;
import apps.view.pages.messaging.groupMessaging.CreateGroupPage;
import apps.view.pages.messaging.groupMessaging.CreateNewGroupMessagePage;
import apps.view.pages.messaging.groupMessaging.GroupHistory;
import apps.view.pages.messaging.multipleMessaging.CreateMultipleMessagePage;
import apps.view.pages.messaging.multipleMessaging.EditCategoryPage;
import apps.view.pages.timeline.ForwardMessage;
import apps.view.pages.timeline.TweetPage;
import apps.view.pages.timeline.WriteComment;
import controller.LogicalAgent;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.messagingEvents.pv.LoadMessagesInPVEvent;
import shared.models.*;
import shared.responses.messagingResponses.pv.LoadMessagesInPVResponse;

import java.io.IOException;
import java.util.ArrayList;

public class ExtraPageChangers {

    public static void goToWriteCommentPage (TweetCopy tweetCopy) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "writeComment")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            assert root != null;
            Scene scene = new Scene(root);
            WriteComment writeCommentController = fxmlLoader.getController();
            writeCommentController.setCurrentTweet(tweetCopy);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToForwardMessagePage (TweetCopy currentTweet) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "forwardMessage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            assert root != null;
            Scene scene = new Scene(root);
            ForwardMessage forwardMessage = fxmlLoader.getController();
            forwardMessage.setCurrentTweet(currentTweet);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToMessageHistory (ArrayList<UserCopy> usersInChat) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "messageHistory")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            assert root != null;
            Scene scene = new Scene(root);
            MessageHistory messageHistory = fxmlLoader.getController();
            messageHistory.setUsersInChat(usersInChat);
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            LoadMessagesInPVEvent loadMessagesInPVEvent = new LoadMessagesInPVEvent(LogicalAgent.authToken , usersInChat.get(1));
            Runnable runnable = () -> {
                LoadMessagesInPVResponse loadMessagesInPVResponse = (LoadMessagesInPVResponse) socketEventSender.request(loadMessagesInPVEvent);
                messageHistory.showMessages(loadMessagesInPVResponse.getMessages());
            };
            ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
            LogicalAgent.responseVisitors.add(responseVisitor);
            responseVisitor.start();
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToGroupHistory (Group group) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "groupHistory")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            assert root != null;
            Scene scene = new Scene(root);
            GroupHistory groupHistory = fxmlLoader.getController();
            groupHistory.setGroup(group);
            GroupMessagingController groupMessagingController = new GroupMessagingController();
            Runnable runnable = () -> {
                groupHistory.setUsersInChat(groupMessagingController.getAllUsersOfGroup(group));
                groupHistory.setMessages(groupMessagingController.getAllMessagesOfGroup(group));
                groupHistory.showMessages();
            };
            ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
            responseVisitor.start();
            LogicalAgent.responseVisitors.add(responseVisitor);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToProfilePage (UserCopy selectedUser) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "profilePage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            ProfilePage profilePage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            profilePage.setSelectedUser(selectedUser);
            profilePage.profileStartPageListener.eventOccurred(profilePage);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToEditMessagePage (MessageCopy message) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "editMessagePage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            EditMessagePage editMessagePage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            editMessagePage.setMessage(message);
            editMessagePage.getTextArea().setText(message.getText());
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToCreateNewMessagePage (ArrayList<UserCopy> usersInChat) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "createNewMessage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            CreateNewMessage createNewMessage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            createNewMessage.setUsersInChat(usersInChat);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToCreateNewGroupMessagePage (Group group) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "createNewGroupMessagePage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            CreateNewGroupMessagePage createNewGroupMessagePage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            createNewGroupMessagePage.setGroup(group);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToCreateMultipleMessage (ArrayList<UserCopy> usersToBeSent) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "createMultipleMessagePage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            CreateMultipleMessagePage multipleMessagePage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            multipleMessagePage.setUsersToBeSent(usersToBeSent);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToEditCategoryPage (ArrayList<UserCopy> members , String categoryName) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "editCategoryPage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            EditCategoryPage editCategoryPage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            editCategoryPage.setMembers(members);
            editCategoryPage.setCategoryName(categoryName);
            editCategoryPage.showMembers();
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToCreateNewGroupPage (ArrayList<Group> allGroups) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "createGroupPage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            CreateGroupPage createGroupPage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            createGroupPage.setAllGroups(allGroups);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToAddGroupMemberPage (Group group) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "addGroupMemberPage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            AddGroupMemberPage addGroupMemberPage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            addGroupMemberPage.setGroup(group);
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }

    public static void goToTweetPage (TweetCopy tweetCopy) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "tweetPage")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            TweetPage tweetPage = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            tweetPage.setCurrentTweet(tweetCopy);
            tweetPage.updateScene();
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });
    }

    public static void goToTimeLineListPage (ArrayList<TweetCopy> tweets) {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ViewManager.class.getResource(LogicalAgent.viewManager.getFxmlConfig().getProperty(String.class , "timelinelist")));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            TimeLineList timeLine = fxmlLoader.getController();
            assert root != null;
            Scene scene = new Scene(root);
            timeLine.setTweetList(tweets);
            timeLine.showTheList();
            LogicalAgent.viewManager.getScenes().push(scene);
            LogicalAgent.viewManager.getMainStage().setScene(scene);
        });

    }
}
