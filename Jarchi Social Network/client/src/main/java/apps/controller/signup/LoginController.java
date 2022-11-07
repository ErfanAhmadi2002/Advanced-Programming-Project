package apps.controller.signup;

import apps.view.pages.signup.Login;
import controller.Controller;
import controller.LogicalAgent;
import network.SocketEventSender;
import shared.events.signUpEvents.LoginEvent;
import shared.formEvents.LoginFormEvent;
import shared.responses.signUpResponses.LoginResponse;

import java.io.IOException;


public class LoginController extends Controller {

    public LoginController() {
    }

    public void login(LoginFormEvent loginFormEvent , Login login) {
        SocketEventSender socketEventSender = LogicalAgent.socketEventSender;
        LoginEvent loginEvent = new LoginEvent(0 , loginFormEvent);
        LoginResponse loginResponse = (LoginResponse) socketEventSender.request(loginEvent);
        if (!loginResponse.isValidInput()){
            login.invalidInputError();
        }else {
            LogicalAgent.authToken = loginResponse.getAuthToken();
            LogicalAgent.viewManager.setUser(loginResponse.getUser());
            LogicalAgent.viewManager.LoadScene("mainMenu");
            LogicalAgent.offlineController.sendSavedEvents(LogicalAgent.socketEventSender , LogicalAgent.authToken);
        }
    }
}
