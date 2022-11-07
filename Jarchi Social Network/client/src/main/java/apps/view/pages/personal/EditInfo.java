package apps.view.pages.personal;

import apps.listeners.generalListeners.ExitProgramListener;
import apps.listeners.generalListeners.GoBackListener;
import apps.listeners.generalListeners.GoToMainMenuListener;
import apps.listeners.personalListeners.editInfo.SubmitNewFirstNameListener;
import apps.listeners.personalListeners.editInfo.SubmitNewLastNameListener;
import apps.listeners.personalListeners.editInfo.UploadProfileImageListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import listeners.ButtonListener;
import listeners.FormListener;
import listeners.PageListener;
import shared.formEvents.NewNameFormEvent;
import view.Page;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditInfo extends Page implements Initializable {
    private ButtonListener backButtonListener;
    private ButtonListener mainMenuListener;
    private ButtonListener exitProgramListener;
    private FormListener submitFirstNameFormListener;
    private FormListener submitLastNameFormListener;
    private PageListener uploadProfileImageListener;
    private File imageFile;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private Button mainMenu;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button submitFirstName;

    @FXML
    private Button submitLastName;

    @FXML
    private Button upload;

    public ImageView getImageView() {
        return imageView;
    }

    public Button getUpload() {
        return upload;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        backButtonListener.buttonPressed();
    }

    @FXML
    void upload(ActionEvent event) throws IOException{
        chooseProfileImage();
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
    void submitFirstName(ActionEvent event) throws IOException {
        NewNameFormEvent newFirstName = new NewNameFormEvent(firstName.getText());
        submitFirstNameFormListener.formRequest(newFirstName , this);
    }

    @FXML
    void submitLastName(ActionEvent event) throws IOException {
        NewNameFormEvent newLastName = new NewNameFormEvent(lastName.getText());
        submitLastNameFormListener.formRequest(newLastName , this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backButtonListener = new GoBackListener();
        this.mainMenuListener = new GoToMainMenuListener();
        this.exitProgramListener = new ExitProgramListener();
        this.submitFirstNameFormListener = new SubmitNewFirstNameListener();
        this.submitLastNameFormListener = new SubmitNewLastNameListener();
        this.uploadProfileImageListener = new UploadProfileImageListener();
    }

    private void chooseProfileImage () throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files" , "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            setImageFile(file);
            BufferedImage image = ImageIO.read(file);
            Image profile = SwingFXUtils.toFXImage(image, null);
            this.imageView.setImage(profile);
            this.upload.setVisible(false);
            uploadProfileImageListener.eventOccurred(this);
        }
    }
}
