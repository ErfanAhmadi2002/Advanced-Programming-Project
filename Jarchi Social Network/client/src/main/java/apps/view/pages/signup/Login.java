package apps.view.pages.signup;


import apps.listeners.signUpListeners.LoginFormListener;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import listeners.FormListener;
import shared.formEvents.LoginFormEvent;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends Page implements Initializable {
    private FormListener loginFormListener;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    @FXML
    private Button login;

    @FXML
    void login(ActionEvent event) throws IOException {
        LoginFormEvent loginFormEvent = new LoginFormEvent(username.getText(), password.getText());
        loginFormListener.formRequest(loginFormEvent , this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginFormListener = new LoginFormListener();
    }

    public void invalidInputError(){
        Config config = Config.getConfig("errorMessages");
        errorLabel.setText(config.getProperty(String.class , "incorrect"));
    }
}
