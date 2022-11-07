package apps.view.pages.generalPages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.generalListeners.profileListeners.*;
import controller.LogicalAgent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePage extends Page implements Initializable {
    private UserCopy selectedUser;
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    public  PageListener profileStartPageListener;
    private PageListener blockButtonListener;
    private PageListener followButtonListener;
    private PageListener muteButtonListener;
    private PageListener sendMessageButtonListener;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private ImageView profileImage;

    @FXML
    private Text name;

    @FXML
    private Text username;

    @FXML
    private Text emailAddress;

    @FXML
    private Text birthdate;

    @FXML
    private Text phoneNumber;

    @FXML
    private Text state;

    @FXML
    private Text lastSeen;

    @FXML
    private Text followingState;

    @FXML
    private Button followButton;

    @FXML
    private Button block;

    @FXML
    private Button mute;

    @FXML
    private Button sendMessage;

    public UserCopy getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserCopy selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Text getName() {
        return name;
    }

    public Text getUsername() {
        return username;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        LogicalAgent.finishFirstThread();
        backButtonListener.buttonPressed();
    }

    @FXML
    void block(ActionEvent event) {
        blockButtonListener.eventOccurred(this);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitProgramListener.buttonPressed();
    }

    @FXML
    void followButton(ActionEvent event) {
        followButtonListener.eventOccurred(this);
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        mainMenuListener.buttonPressed();
    }

    @FXML
    void sendMessage(ActionEvent event) {
        sendMessageButtonListener.eventOccurred(this);
    }

    @FXML
    void mute(ActionEvent event) {
        muteButtonListener.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.profileStartPageListener = new ProfileStartPageListener();
        this.blockButtonListener = new BlockButtonListener();
        this.followButtonListener = new FollowButtonListener();
        this.muteButtonListener = new MuteButtonListener();
        this.sendMessageButtonListener = new SendMessageButtonListener();
    }

    public void setFollowButtonText(boolean state) {
        Platform.runLater(() -> {
            if (state) {
                followButton.setText("Unfollow");
            } else {
                followButton.setText("Follow");
            }
        });
    }

    public void setBlockButtonText(boolean state) {
        if (state) {
            block.setText("Block");
        } else {
            block.setText("Unblock");
        }
    }

    public void setMuteButtonText(boolean state) {
        if (state) {
            mute.setText("Mute");
        } else {
            mute.setText("UnMute");
        }
    }

    public void setFollowLabelText(boolean state) {
        if (state) {
            followingState.setText("You Follow this user");
        } else {
            followingState.setText("You Don't Follow this user");
        }
    }

    public void setOnlineStateText (boolean state){
        if (state){
            this.state.setText("Online");
        } else {
            this.state.setText("Offline");
        }
    }

    public void setBirthDayLabelText(String birthdateText) {
        birthdate.setText(birthdateText);
    }

    public void setEmailLabelText(String emailText) {
        emailAddress.setText(emailText);
    }

    public void setPhoneNumberLabelText(String phoneNumberText) {
        phoneNumber.setText(phoneNumberText);
    }

    public void setLastSeenLabelText(String lastSeenText) {
        lastSeen.setText(lastSeenText);
    }

    public void setLoadedImage(Image image) {
        profileImage.setImage(image);
    }
}
