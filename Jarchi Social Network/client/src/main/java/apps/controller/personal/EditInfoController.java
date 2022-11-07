package apps.controller.personal;

import apps.view.pages.personal.EditInfo;
import apps.view.pages.personal.MyInfo;
import controller.Controller;
import controller.LogicalAgent;
import javafx.scene.image.Image;
import network.SocketEventSender;
import shared.events.generalEvents.LoadImageEvent;
import shared.events.personalEvents.ChangeFirstNameEvent;
import shared.events.personalEvents.ChangeLastNameEvent;
import shared.events.personalEvents.UploadProfileImageEvent;
import shared.events.settingEvents.ShowMyInfoEvent;
import shared.formEvents.NewNameFormEvent;
import shared.models.UserCopy;
import shared.responses.generalResponses.LoadImageResponse;
import shared.responses.personalResponses.ChangeFirstNameResponse;
import shared.responses.personalResponses.ChangeLastNameResponse;
import shared.responses.personalResponses.UploadProfileImageResponse;
import shared.responses.settingResponses.ShowMyInfoResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditInfoController extends Controller {

    public EditInfoController() {
    }

    public void changeFirstName (NewNameFormEvent formEvent) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        ChangeFirstNameEvent changeFirstNameEvent = new ChangeFirstNameEvent(LogicalAgent.authToken , formEvent);
        ChangeFirstNameResponse changeFirstNameResponse = (ChangeFirstNameResponse) socketEventSender.request(changeFirstNameEvent);
    }

    public void changeLastName (NewNameFormEvent formEvent) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        ChangeLastNameEvent changeLastNameEvent = new ChangeLastNameEvent(LogicalAgent.authToken , formEvent);
        ChangeLastNameResponse changeLastNameResponse = (ChangeLastNameResponse) socketEventSender.request(changeLastNameEvent);
    }

    public void uploadProfileImage (EditInfo page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        File imageFile = page.getImageFile();
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(imageFile.toPath());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        UploadProfileImageEvent uploadProfileImageEvent = new UploadProfileImageEvent(LogicalAgent.authToken , bytes);
        UploadProfileImageResponse uploadProfileImageResponse = (UploadProfileImageResponse) socketEventSender.request(uploadProfileImageEvent);
    }

    public void showProfileMyPicture (MyInfo page) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        UserCopy user = LogicalAgent.viewManager.getUser();
        LoadImageEvent loadImageEvent = new LoadImageEvent(LogicalAgent.authToken , user.getImageId());
        LoadImageResponse loadImageResponse = (LoadImageResponse) socketEventSender.request(loadImageEvent);
        if (loadImageResponse.getImage() != null) {
            Image image = loadImageResponse.getImage().convertToImage();
            page.setLoadedImage(image);
        }
        ShowMyInfoEvent showMyInfoEvent = new ShowMyInfoEvent(LogicalAgent.authToken);
        ShowMyInfoResponse showMyInfoResponse = (ShowMyInfoResponse) socketEventSender.request(showMyInfoEvent);
        page.showInfo(showMyInfoResponse.getName(),
                showMyInfoResponse.getUserName(),
                showMyInfoResponse.getPhoneNumber(),
                showMyInfoResponse.getEmailAddress());
    }
}
