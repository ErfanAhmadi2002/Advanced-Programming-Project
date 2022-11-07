package apps.view.pages.personal.followings;

import apps.listeners.generalListeners.profileListeners.GoToProfileListener;
import apps.listeners.personalListeners.lists.UnfollowListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import listeners.ProfileListener;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FollowingPane extends Page implements Initializable {
    private UserCopy user;
    private ProfileListener showProfileListener;
    private PageListener unfollowListener;

    @FXML
    private Text username;

    @FXML
    private Button profile;

    @FXML
    private Button unfollow;

    public void setUser(UserCopy user) {
        this.user = user;
    }

    public UserCopy getUser() {
        return user;
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        showProfileListener.eventOccurred(user);
    }

    @FXML
    void unfollow(ActionEvent event)throws IOException{
        unfollowListener.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProfileListener = new GoToProfileListener();
        unfollowListener = new UnfollowListener();
    }

    public void startPage (UserCopy user){
        setUser(user);
        username.setText(user.getUserName());
    }
}
