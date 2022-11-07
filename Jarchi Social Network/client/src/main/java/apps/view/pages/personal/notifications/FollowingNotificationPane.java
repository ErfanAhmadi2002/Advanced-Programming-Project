package apps.view.pages.personal.notifications;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import shared.models.UserCopy;
import view.Page;

public class FollowingNotificationPane extends Page {

    @FXML
    private Text message;

    public void startPage (UserCopy user , boolean state){
        if (state) {
            message.setText("The user " + user.getUserName() + " followed You");
        }
        else {
            message.setText("The user " + user.getUserName() + " unfollowed You");
        }
    }
}
