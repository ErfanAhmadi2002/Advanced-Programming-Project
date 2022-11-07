package apps.listeners.generalListeners.profileListeners;


import listeners.ProfileListener;
import shared.models.User;
import shared.models.UserCopy;
import view.ExtraPageChangers;

import java.io.IOException;


public class GoToProfileListener implements ProfileListener {
    @Override
    public void eventOccurred(UserCopy user) throws IOException {
        ExtraPageChangers.goToProfilePage(user);
    }
}
