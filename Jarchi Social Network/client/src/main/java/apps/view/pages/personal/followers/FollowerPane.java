package apps.view.pages.personal.followers;

import apps.listeners.generalListeners.profileListeners.GoToProfileListener;
import controller.LogicalAgent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.ProfileListener;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FollowerPane extends Page implements Initializable {
    private UserCopy user;
    private ProfileListener showProfileListener;

    @FXML
    private Text username;

    @FXML
    private Button profile;

    public UserCopy getUser() {
        return user;
    }

    public void setUser(UserCopy user) {
        this.user = user;
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        LogicalAgent.pauseFirstThread();
        showProfileListener.eventOccurred(user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProfileListener = new GoToProfileListener();
    }

    public void startPage (UserCopy user){
        setUser(user);
        username.setText(user.getUserName());
    }

}
