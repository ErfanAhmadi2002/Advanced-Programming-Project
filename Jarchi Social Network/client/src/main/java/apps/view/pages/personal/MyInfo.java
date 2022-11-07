package apps.view.pages.personal;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.generalListeners.profileListeners.ShowProfilePictureMyInfoListener;
import controller.LogicalAgent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyInfo extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private PageListener showProfilePictureMyInfoListener;

    @FXML
    private Text name;

    @FXML
    private Text username;

    @FXML
    private Text password;

    @FXML
    private Text emailAddress;

    @FXML
    private Text phoneNumber;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    void back(ActionEvent event) throws IOException {
        backButtonListener.buttonPressed();
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitProgramListener.buttonPressed();
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        mainMenuListener.buttonPressed();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.showProfilePictureMyInfoListener = new ShowProfilePictureMyInfoListener();
        showProfilePictureMyInfoListener.eventOccurred(this);
    }

    public void showInfo(String name, String userName, String phoneNumber, String emailAddress) {
        this.name.setText(name);
        this.username.setText(userName);
        this.emailAddress.setText(emailAddress);
        this.phoneNumber.setText(phoneNumber);
    }

    public void setLoadedImage(Image image) {
        profileImage.setImage(image);
    }
}
