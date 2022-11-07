package shared.responses.messagingResponses.functionalItems;

import shared.models.UserCopy;

import java.util.ArrayList;

public class ItemMessage2 {
    private final String categoryName;
    private final ArrayList<UserCopy> usersInCategory;

    public ItemMessage2(String categoryName, ArrayList<UserCopy> usersInCategory) {
        this.categoryName = categoryName;
        this.usersInCategory = usersInCategory;
    }

    public ArrayList<UserCopy> getUsersInCategory() {
        return usersInCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
