package apps.view.pages.signup;
import apps.listeners.signUpListeners.RegistrationFormListener;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import listeners.FormListener;
import shared.formEvents.RegistrationFormEvent;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Register extends Page implements Initializable {
    private FormListener registrationFormListener;

    @FXML
    private TextField username;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField password;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField birthYear;
    @FXML
    private TextField birthMonth;
    @FXML
    private TextField birthDay;
    @FXML
    private Button register;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label birthdateLabel;

    @FXML
    void register(ActionEvent event) throws IOException {
        if (checkLabelsValidity()) {
            RegistrationFormEvent registrationFormEvent = new RegistrationFormEvent(
                    firstName.getText(),
                    lastName.getText(),
                    username.getText(),
                    password.getText(),
                    birthDay.getText(),
                    birthMonth.getText(),
                    birthYear.getText(),
                    emailAddress.getText(),
                    phoneNumber.getText()
            );
            registrationFormListener.formRequest(registrationFormEvent, this);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registrationFormListener = new RegistrationFormListener();
    }

    public boolean checkLabelsValidity (){
        boolean isValid = true;
        Config config = Config.getConfig("errorMessages");
        if (username.getText().equals("")){
            userNameLabel.setText(config.getProperty(String.class , "emptyField"));
            isValid = false;
        }else {userNameLabel.setText("");}

        if (password.getText().equals("")){
            passwordLabel.setText(config.getProperty(String.class , "emptyField"));
            isValid = false;
        }else {passwordLabel.setText("");}

        if (firstName.getText().equals("")){
            firstNameLabel.setText(config.getProperty(String.class , "emptyField"));
            isValid = false;
        }else {firstNameLabel.setText("");}

        if (lastName.getText().equals("")){
            lastNameLabel.setText(config.getProperty(String.class , "emptyField"));
            isValid = false;
        }else {lastNameLabel.setText("");}

        if (emailAddress.getText().equals("")){
            emailAddressLabel.setText(config.getProperty(String.class , "emptyField"));
            isValid = false;
        }else {emailAddressLabel.setText("");}

        if (!birthDay.getText().equals("") || !birthMonth.getText().equals("") || !birthYear.getText().equals("")){
            try {
                Integer.valueOf(birthDay.getText());
                Integer.valueOf(birthMonth.getText());
                Integer.valueOf(birthYear.getText());
            }catch (Throwable throwable){
                birthdateLabel.setText(config.getProperty(String.class , "digitNumber"));
                isValid = false;
            }
        }else {birthdateLabel.setText("");}

        return isValid;
    }

    public void usernameUsedBeforeError (){
        Config config = Config.getConfig("errorMessages");
        userNameLabel.setText(config.getProperty(String.class , "userNameExist"));
    }

    public void emailUsedBeforeError (){
        Config config = Config.getConfig("errorMessages");
        emailAddressLabel.setText(config.getProperty(String.class , "emailAddressExist"));
    }

    public void phoneNumberUsedBeforeError (){
        Config config = Config.getConfig("errorMessages");
        phoneNumberLabel.setText(config.getProperty(String.class , "phoneNumberExist"));
    }
}
