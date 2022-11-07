package apps.controller.messaging;

import apps.view.pages.messaging.multipleMessaging.*;
import config.Config;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.messagingEvents.multiple.*;
import shared.events.personalEvents.LoadFollowersEvent;
import shared.models.UserCopy;
import shared.responses.messagingResponses.multiple.*;
import shared.responses.personalResponses.LoadFollowersResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class MultipleMessagingController extends Controller {

    public MultipleMessagingController() {
    }

    public void startMultipleMessaging (MultipleMessaging page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        StartMultipleMessagingEvent startMultipleMessagingEvent = new StartMultipleMessagingEvent(LogicalAgent.authToken);
        StartMultipleMessagingResponse startMultipleMessagingResponse = (StartMultipleMessagingResponse) socketEventSender.request(startMultipleMessagingEvent);
        HashMap<String , ArrayList<UserCopy>> allCategories = startMultipleMessagingResponse.convertToMap();
        page.setAllCategories(allCategories);
    }

    public void sendMessageToGroup (CreateMultipleMessagePage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        String text = page.getMessageTextArea().getText();
        UserCopy sender = LogicalAgent.viewManager.getUser();
        ArrayList<UserCopy> receivers = page.getUsersToBeSent();
        SendMessageToGroupEvent sendMessageToGroupEvent;
        if (page.getImage() != null){
            File imageFile = page.getImageFile();
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(imageFile.toPath());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            sendMessageToGroupEvent = new SendMessageToGroupEvent(LogicalAgent.authToken , sender , receivers , text , bytes);
        }else {sendMessageToGroupEvent = new SendMessageToGroupEvent(LogicalAgent.authToken , sender , receivers , text , null);}
        SendMessageToGroupResponse sendMessageToGroupResponse = (SendMessageToGroupResponse) socketEventSender.request(sendMessageToGroupEvent);
    }

    public ArrayList<UserCopy> getAllFollowers (){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoadFollowersEvent loadFollowersEvent = new LoadFollowersEvent(LogicalAgent.authToken);
        LoadFollowersResponse loadFollowersResponse = (LoadFollowersResponse) socketEventSender.request(loadFollowersEvent);
        return loadFollowersResponse.getFollowers();
    }

    public void removeCategory (CategoryPane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        RemoveCategoryEvent removeCategoryEvent = new RemoveCategoryEvent(LogicalAgent.authToken , pane.getName());
        RemoveCategoryResponse removeCategoryResponse = (RemoveCategoryResponse) socketEventSender.request(removeCategoryEvent);
        if (removeCategoryResponse != null)pane.categoryRemoved();
    }

    public void removeMemberFromCategory (CategoryMemberPane pane) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        RemoveMemberFromCategoryEvent removeMemberFromCategoryEvent = new RemoveMemberFromCategoryEvent(LogicalAgent.authToken, pane.getCategoryName(), pane.getMember());
        RemoveMemberFromCategoryResponse removeMemberFromCategoryResponse = (RemoveMemberFromCategoryResponse) socketEventSender.request(removeMemberFromCategoryEvent);
    }

    public void addMemberToOldCategory(EditCategoryPage page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        AddMemberToCategoryEvent addMemberToCategoryEvent = new AddMemberToCategoryEvent(LogicalAgent.authToken , page.getCategoryName() , page.getUsername().getText(),true);
        AddMemberToCategoryResponse addMemberToCategoryResponse = (AddMemberToCategoryResponse) socketEventSender.request(addMemberToCategoryEvent);
        Config config = Config.getConfig("errorMessages");
        if (addMemberToCategoryResponse.isAddResult()){
            page.getUserNameLabel().setText("");
        }else {page.getUserNameLabel().setText(config.getProperty(String.class , "sorry"));}
    }

    public void addMemberToNewCategory(CreateCategoryPage page){
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        AddMemberToCategoryEvent addMemberToCategoryEvent = new AddMemberToCategoryEvent(LogicalAgent.authToken , page.getCategoryName().getText() , page.getUsername().getText(),false);
        AddMemberToCategoryResponse addMemberToCategoryResponse = (AddMemberToCategoryResponse) socketEventSender.request(addMemberToCategoryEvent);
        Config config = Config.getConfig("errorMessages");
        if (addMemberToCategoryResponse.isAddResult()){
            page.getUserNameLabel().setText("");
        }else {page.getUserNameLabel().setText(config.getProperty(String.class , "sorry"));}
    }

    public void createNewCategoryName (CreateCategoryPage page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        CreateNewCategoryNameEvent createNewCategoryNameEvent = new CreateNewCategoryNameEvent(LogicalAgent.authToken , page.getCategoryName().getText());
        CreateNewCategoryNameResponse createNewCategoryNameResponse = (CreateNewCategoryNameResponse) socketEventSender.request(createNewCategoryNameEvent);
        Config config = Config.getConfig("errorMessages");
        if (createNewCategoryNameResponse.isSuccessful()){
            page.getCategoryNameLabel().setText("");
        }else {
            page.getCategoryNameLabel().setText(config.getProperty(String.class , "categoryExists"));
        }
    }
}
