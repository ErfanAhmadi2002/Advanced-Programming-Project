package apps.listeners.explorerListeners;

import apps.controller.explorer.SearchAccountController;
import apps.view.pages.explorer.SearchAccount;
import listeners.PageListener;
import view.Page;

import java.io.IOException;

public class SearchAccountListener implements PageListener {
    private SearchAccountController searchAccountController;
    @Override
    public void eventOccurred(Page source) {
        searchAccountController = new SearchAccountController();
        searchAccountController.searchForAccount((SearchAccount) source);
    }
}
