package listeners;


import shared.models.UserCopy;

import java.io.IOException;

public interface ProfileListener {
    void eventOccurred (UserCopy user) throws IOException;
}
