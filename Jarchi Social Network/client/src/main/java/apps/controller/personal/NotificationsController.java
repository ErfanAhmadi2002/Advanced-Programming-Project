package apps.controller.personal;

import apps.view.pages.personal.notifications.FollowingState;
import apps.view.pages.personal.notifications.MyRequestsState;
import apps.view.pages.personal.notifications.NewRequests;
import apps.view.pages.personal.notifications.NewRequestsNotificationPane;
import controller.Controller;
import controller.LogicalAgent;
import network.ResponseVisitor;
import network.SocketEventSender;
import shared.events.personalEvents.notification.*;
import shared.models.UserCopy;
import shared.responses.personalResponses.notification.*;

import java.util.ArrayList;
import java.util.HashMap;


public class NotificationsController extends Controller {

    public NotificationsController() {
    }

    public void startFollowingStatePage (FollowingState page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadFollowingStateEvent loadFollowingStateEvent = new LoadFollowingStateEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            LoadFollowingStateResponse loadFollowingStateResponse = (LoadFollowingStateResponse) socketEventSender.request(loadFollowingStateEvent);
            HashMap<UserCopy, Boolean> followOrUnfollow = loadFollowingStateResponse.convertToMap();
            page.showFollowers(followOrUnfollow);
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void startMyRequestsPage (MyRequestsState page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadMyRequestsEvent loadMyRequestsEvent = new LoadMyRequestsEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            LoadMyRequestsResponse loadMyRequestsResponse = (LoadMyRequestsResponse) socketEventSender.request(loadMyRequestsEvent);
            HashMap<UserCopy , Integer> myRequests = loadMyRequestsResponse.convertToMap();
            page.showMyRequests(myRequests);
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void startNewRequestsPage (NewRequests page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadNewRequestsEvent loadNewRequestsEvent = new LoadNewRequestsEvent(LogicalAgent.authToken);
        Runnable runnable = () -> {
            synchronized (LogicalAgent.syncHandler.REQUEST_SYNC) {
                LoadNewRequestsResponse loadNewRequestsResponse = (LoadNewRequestsResponse) socketEventSender.request(loadNewRequestsEvent);
                ArrayList<UserCopy> newRequests = loadNewRequestsResponse.getUserCopies();
                page.showNewRequests(newRequests);
            }
        };
        ResponseVisitor responseVisitor = new ResponseVisitor(runnable , 1000);
        responseVisitor.start();
        LogicalAgent.responseVisitors.add(responseVisitor);
    }

    public void acceptRequest (NewRequestsNotificationPane pane) {
        synchronized (LogicalAgent.syncHandler.REQUEST_SYNC) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            AnswerRequestEvent answerRequestEvent = new AnswerRequestEvent(LogicalAgent.authToken , 1 , pane.getRequested());
            AnswerRequestResponse answerRequestResponse = (AnswerRequestResponse) socketEventSender.request(answerRequestEvent);
        }
    }

    public void rejectRequest (NewRequestsNotificationPane pane) {
        synchronized (LogicalAgent.syncHandler.REQUEST_SYNC) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            AnswerRequestEvent answerRequestEvent = new AnswerRequestEvent(LogicalAgent.authToken , 2 , pane.getRequested());
            AnswerRequestResponse answerRequestResponse = (AnswerRequestResponse) socketEventSender.request(answerRequestEvent);
        }
    }

    public void safeRejectRequest (NewRequestsNotificationPane pane) {
        synchronized (LogicalAgent.syncHandler.REQUEST_SYNC) {
            SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
            AnswerRequestEvent answerRequestEvent = new AnswerRequestEvent(LogicalAgent.authToken , 3 , pane.getRequested());
            AnswerRequestResponse answerRequestResponse = (AnswerRequestResponse) socketEventSender.request(answerRequestEvent);
        }
    }

    public void deleteSeenNotificationMyRequests () {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        DeleteSeenNotificationsEvent deleteSeenNotificationsEvent = new DeleteSeenNotificationsEvent(LogicalAgent.authToken , 1);
        DeleteSeenNotificationResponse deleteSeenNotificationResponse = (DeleteSeenNotificationResponse) socketEventSender.request(deleteSeenNotificationsEvent);
    }

    public void deleteSeenNotificationFollowingState () {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        DeleteSeenNotificationsEvent deleteSeenNotificationsEvent = new DeleteSeenNotificationsEvent(LogicalAgent.authToken , 2);
        DeleteSeenNotificationResponse deleteSeenNotificationResponse = (DeleteSeenNotificationResponse) socketEventSender.request(deleteSeenNotificationsEvent);
    }
}
