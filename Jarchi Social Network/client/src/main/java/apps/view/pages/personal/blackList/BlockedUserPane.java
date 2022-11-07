package apps.view.pages.personal.blackList;

import apps.listeners.generalListeners.profileListeners.GoToProfileListener;
import apps.listeners.personalListeners.lists.UnblockListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import listeners.ProfileListener;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BlockedUserPane extends Page implements Initializable {
    private UserCopy user;
    private ProfileListener showProfileListener;
    private PageListener unblockListener;

    @FXML
    private Text username;

    @FXML
    private Button profile;

    @FXML
    private Button unblock;

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
    void unblock(ActionEvent event)throws IOException{
        unblockListener.eventOccurred(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProfileListener = new GoToProfileListener();
        unblockListener = new UnblockListener();
    }

    public void startPage (UserCopy user){
        setUser(user);
        username.setText(user.getUserName());
    }
}
