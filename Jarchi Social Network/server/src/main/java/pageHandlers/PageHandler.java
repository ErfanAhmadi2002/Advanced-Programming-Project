package pageHandlers;

import managers.ClientManager;

public abstract class PageHandler {
    public final ClientManager clientManager;

    public PageHandler(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
}
