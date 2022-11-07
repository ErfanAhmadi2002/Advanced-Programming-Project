package apps.view.pages.messaging.multipleMessaging;

import apps.listeners.messagingListeners.multipleMessaging.RemoveMemberFromCategoryListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import listeners.PageListener;
import shared.models.User;
import shared.models.UserCopy;
import view.Page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryMemberPane extends Page implements Initializable {
    private UserCopy member;
    private String categoryName;
    private PageListener removeButtonListener;

    @FXML
    private Text username;

    @FXML
    private Button remove;

    @FXML
    void remove(ActionEvent event) throws IOException {
        removeButtonListener.eventOccurred(this);
    }

    public void setMember(UserCopy member) {
        this.member = member;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public UserCopy getMember() {
        return member;
    }

    public void startPage(String categoryName , UserCopy member){
        setCategoryName(categoryName);
        setMember(member);
        username.setText(member.getUserName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        removeButtonListener = new RemoveMemberFromCategoryListener();
    }
}
