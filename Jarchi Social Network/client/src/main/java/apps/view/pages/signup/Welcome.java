package apps.view.pages.signup;

import apps.listeners.welcomeListeners.GoToLoginPageListener;
import apps.listeners.welcomeListeners.GoToRegistrationPageListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import listeners.ButtonListener;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Welcome extends Page implements Initializable {
   ButtonListener GotoRegistrationPage;
   ButtonListener GotoLoginPage;

 @FXML
 private Button Login;

 @FXML
 private Button Register;

 @FXML
 void Login(ActionEvent event) throws IOException {
     GotoLoginPage.buttonPressed();
 }

 @FXML
 void Resister(ActionEvent event) throws IOException {
     GotoRegistrationPage.buttonPressed();
 }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GotoRegistrationPage = new GoToRegistrationPageListener();
        GotoLoginPage = new GoToLoginPageListener();
    }
}
