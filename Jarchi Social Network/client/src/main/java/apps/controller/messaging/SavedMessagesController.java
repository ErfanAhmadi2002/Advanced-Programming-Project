package apps.controller.messaging;

import apps.view.pages.messaging.savedMessages.SavedMessages;
import apps.view.pages.messaging.savedMessages.SavedMessagesPane;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.messagingEvents.pv.LoadSaveMessagePaneEvent;
import shared.events.messagingEvents.pv.StartSavedMessagesEvent;
import shared.responses.messagingResponses.pv.LoadSaveMessagePaneResponse;
import shared.responses.messagingResponses.pv.StartSavedMessagesResponse;


public class SavedMessagesController extends Controller {

    public SavedMessagesController() {
    }

    public void startSavedMessage (SavedMessages page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        StartSavedMessagesEvent startSavedMessagesEvent = new StartSavedMessagesEvent(LogicalAgent.authToken);
        StartSavedMessagesResponse startSavedMessagesResponse = (StartSavedMessagesResponse) socketEventSender.request(startSavedMessagesEvent);
        page.setSavedMessages(startSavedMessagesResponse.getAllMessages());
    }

    public void startSavedMessagesPane (SavedMessagesPane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadSaveMessagePaneEvent loadSaveMessagePaneEvent = new LoadSaveMessagePaneEvent(LogicalAgent.authToken , pane.getTweet().getId());
        LoadSaveMessagePaneResponse loadSaveMessagePaneResponse = (LoadSaveMessagePaneResponse) socketEventSender.request(loadSaveMessagePaneEvent);
        pane.setWriter(loadSaveMessagePaneResponse.getUserCopy());
        if (loadSaveMessagePaneResponse.getImage() != null){
            pane.setLoadedImage(loadSaveMessagePaneResponse.getImage().convertToImage());
        }
    }
}
