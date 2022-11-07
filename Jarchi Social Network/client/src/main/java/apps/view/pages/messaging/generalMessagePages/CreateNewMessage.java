package apps.view.pages.messaging.generalMessagePages;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.messagingListeners.monoMessaging.WriteNewMessageInPVListener;
import controller.LogicalAgent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import listeners.ButtonListener;
import listeners.PageListener;
import shared.models.UserCopy;
import view.Page;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateNewMessage extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private ArrayList<UserCopy> usersInChat;
    private PageListener submitButtonListener;
    private File imageFile;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private Button upload;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button submit;

    @FXML
    private ImageView image;

    public TextArea getMessageTextArea() {
        return messageTextArea;
    }

    public ImageView getImage() {
        return image;
    }

    public void setUsersInChat(ArrayList<UserCopy> usersInChat) {
        this.usersInChat = usersInChat;
    }

    public ArrayList<UserCopy> getUsersInChat() {
        return usersInChat;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        exitProgramListener.buttonPressed();
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        mainMenuListener.buttonPressed();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        LogicalAgent.continueLastThread();
        backButtonListener.buttonPressed();
    }

    @FXML
    void submit(ActionEvent event) {
        submitButtonListener.eventOccurred(this);
    }

    @FXML
    void upload(ActionEvent event) throws IOException {
        chooseMessageImage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.submitButtonListener = new WriteNewMessageInPVListener();
    }

    private void chooseMessageImage () throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files" , "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            setImageFile(file);
            BufferedImage image = ImageIO.read(file);
            Image profile = SwingFXUtils.toFXImage(image, null);
            this.image.setImage(profile);
            this.upload.setVisible(false);
        }
    }
}
