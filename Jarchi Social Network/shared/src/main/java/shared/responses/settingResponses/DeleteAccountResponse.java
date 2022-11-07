package shared.responses.settingResponses;

import shared.responses.Response;

public class DeleteAccountResponse extends Response {
    private final boolean deleteAccountResult;

    public DeleteAccountResponse(boolean deleteAccountResult) {
        this.deleteAccountResult = deleteAccountResult;
    }

    public boolean isDeleteAccountResult() {
        return deleteAccountResult;
    }
}
